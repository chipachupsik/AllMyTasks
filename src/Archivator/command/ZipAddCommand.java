package Archivator.command;

import Archivator.ConsoleHelper;
import Archivator.ZipFileManager;
import Archivator.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

//команда добавления файла в архив
public class ZipAddCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Добавление файлов в архив.");
            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите имя файда, который необходимо добавить: ");
            Path path = Paths.get(ConsoleHelper.readString());

            zipFileManager.addFile(path);

            ConsoleHelper.writeMessage("Добавление произведено.");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
