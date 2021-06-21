package Archivator.command;


import Archivator.ConsoleHelper;
import Archivator.FileProperties;
import Archivator.ZipFileManager;

import java.util.ArrayList;
import java.util.List;

//команда просмотра содержимого архива
public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива");
        //создаём объект класса ZipFileManager с помощью метода
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");

        List<FileProperties> list = zipFileManager.getFilesList();

        for(FileProperties f : list) {
            ConsoleHelper.writeMessage(f.toString());
        }

        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
