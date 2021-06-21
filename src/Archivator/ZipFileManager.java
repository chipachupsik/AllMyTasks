package Archivator;

import Archivator.exception.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.*;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }


    //метод архивации файла
    public void createZip(Path source) throws Exception {
        //Проверяем, существует ли директория, где будет создаваться архив
        //при необходимости создаём её
        Path zipDirectory = zipFile.getParent();

        if(Files.notExists(zipDirectory)) {
            Files.createDirectories(zipDirectory);
        }

        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if(Files.isDirectory(source)) {
                //Если архивируем директорию, то нужно получить список файлов в ней
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();

                //добавляем каждый полученный файл в архив
                for(Path fileName : fileNames) {
                    addNewZipEntry(zipOutputStream, source, fileName);
                }
            } else if(Files.isRegularFile(source)) {

                //Если архивируем отдельный файл, то нужно получить его директорию и имя
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else {

                //если переданный source не директория и не файл, бросаем исключение
                throw new PathIsNotFoundException();
            }

        }

    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        Path fullPath = filePath.resolve(fileName);//получаем полный путь к файлу

        try(InputStream inputStream = Files.newInputStream(fullPath);) {
            ZipEntry zipEntry = new ZipEntry(fileName.toString());

            zipOutputStream.putNextEntry(zipEntry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<FileProperties> getFilesList() throws Exception {
        if(!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        List<FileProperties> fileProperties = new ArrayList<>();

        try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while(zipEntry != null) {
                //поля "Размер" и "Сжатый размер" не известны, пока элемент не вычитан
                //далее мы вычитываем элемент в созданный буффер
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                copyData(zipInputStream, bos);

                FileProperties fP = new FileProperties(zipEntry.getName(), zipEntry.getSize(), zipEntry.getCompressedSize(), zipEntry.getMethod());
                fileProperties.add(fP);
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        return fileProperties;
    }

    public void extractAll(Path outputFolder) throws Exception {
        // Проверяем существует ли zip файл
        if (!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            // Создаем директорию вывода, если она не существует
            if (Files.notExists(outputFolder))
                Files.createDirectories(outputFolder);

            // Проходимся по содержимому zip потока (файла)
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                Path fileFullName = outputFolder.resolve(fileName);

                // Создаем необходимые директории
                Path parent = fileFullName.getParent();
                if (Files.notExists(parent))
                    Files.createDirectories(parent);

                try (OutputStream outputStream = Files.newOutputStream(fileFullName)) {
                    copyData(zipInputStream, outputStream);
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    public void removeFile(Path path) throws Exception {
        removeFiles(Collections.singletonList(path));
    }

    public void removeFiles(List<Path> pathList) throws Exception {
        if(!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        Path tempFile = Files.createTempFile(null, null);

        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(tempFile))) {
            try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {

                ZipEntry zipEntry = zipInputStream.getNextEntry();

                while(zipEntry != null) {

                    Path archivedFile = Paths.get(zipEntry.getName());

                    if(!pathList.contains(archivedFile)) {
                        String fileName = zipEntry.getName();
                        zipOutputStream.putNextEntry(new ZipEntry(fileName));

                        copyData(zipInputStream, zipOutputStream);

                        zipOutputStream.closeEntry();
                        zipInputStream.closeEntry();
                    } else {
                        ConsoleHelper.writeMessage(String.format("Файл '%s' удалён из архива.", archivedFile.toString()));
                    }

                    zipEntry = zipInputStream.getNextEntry();
                }
            }
        }

        Files.move(tempFile, zipFile, StandardCopyOption.REPLACE_EXISTING);

    }

    public void addFile(Path absolutePath) throws Exception {
        addFiles(Collections.singletonList(absolutePath));
    }

    public void addFiles(List<Path> absolutePathList) throws Exception {
        //Проверяем существует ли файл архива
        if (!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        //Создаём временный файл
        Path tempFile = Files.createTempFile(null, null);
        //Создаём локальный список, для добавления в него путей добавленных в архив файлов
        List<Path> localList = new ArrayList<>();

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(tempFile))) {
            try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {

                ZipEntry zipEntry = zipInputStream.getNextEntry();

                while (zipEntry != null) {
                    localList.add(Paths.get(zipEntry.getName()));

                    zipOutputStream.putNextEntry(zipEntry);
                    copyData(zipInputStream, zipOutputStream);

                    zipOutputStream.closeEntry();
                    zipInputStream.closeEntry();

                    zipEntry = zipInputStream.getNextEntry();
                }
            }

            for (Path p : absolutePathList) {
                if (Files.isRegularFile(p)) {
                    if (localList.contains(p.getFileName())) {
                        ConsoleHelper.writeMessage("Файл уже существует в архиве.");
                    } else {

                        addNewZipEntry(zipOutputStream, p.getParent(), p.getFileName());
                        ConsoleHelper.writeMessage("Файл добавлен в архив.");
                    }
                } else {
                    throw new PathIsNotFoundException();
                }

            }
        }
        Files.move(tempFile, zipFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
