package Archivator.command;

import Archivator.ConsoleHelper;
import Archivator.ZipFileManager;
import Archivator.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

//команда создания архив (упаковки файлов в архив)
public class ZipCreateCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Создание архива.");
            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите полное имя файла или директории: ");
            Path path = Paths.get(ConsoleHelper.readString());

            zipFileManager.createZip(path);
            ConsoleHelper.writeMessage("Архив создан.");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
