package Archivator.command;

import Archivator.ConsoleHelper;
import Archivator.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ZipCommand implements Command {
    public ZipFileManager getZipFileManager() throws Exception {
        ConsoleHelper.writeMessage("Введите полный путь файла архива: ");
        String fullPath = ConsoleHelper.readString();
        Path path = Paths.get(fullPath);

        ZipFileManager result = new ZipFileManager(path);
        return result;
    }
}
