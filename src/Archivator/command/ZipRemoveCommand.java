package Archivator.command;

import Archivator.ConsoleHelper;
import Archivator.ZipFileManager;
import Archivator.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

//команда удаления файла из арихва
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
            ConsoleHelper.writeMessage("Удаление файла из архива.");
            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите имя файда, который необходимо удалить: ");
            Path path = Paths.get(ConsoleHelper.readString());

            zipFileManager.removeFile(path);

            ConsoleHelper.writeMessage("Введёный файл удалён.");
    }
}
