package Archivator;

import Archivator.command.ExitCommand;
import Archivator.exception.WrongZipFileException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Archiver {
    public static void main(String[] args) throws Exception {
        Operation operation = null;

        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);

            } catch (WrongZipFileException e) {
                new ConsoleHelper().writeMessage("Вы не выбрали файл архива или выбали неверный файл.");
            } catch (Exception e) {
                new ConsoleHelper().writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        } while (operation != Operation.EXIT);
    }

    public static Operation askOperation() throws IOException {
        new ConsoleHelper().writeMessage("Выберите операцию: ");
        new ConsoleHelper().writeMessage(Operation.CREATE.ordinal() + " - упаковать файлы в архив");
        new ConsoleHelper().writeMessage(Operation.ADD.ordinal() + " - добавить файл в архив");
        new ConsoleHelper().writeMessage(Operation.REMOVE.ordinal() + " - удалить файл из архива");
        new ConsoleHelper().writeMessage(Operation.EXTRACT.ordinal() + " - распаковать архив");
        new ConsoleHelper().writeMessage(Operation.CONTENT.ordinal() + " - просмотреть содержимое архива");
        new ConsoleHelper().writeMessage(Operation.EXIT.ordinal() + " - выход");

        int number = new ConsoleHelper().readInt();
        Operation result = null;

        switch(number) {
            case 0: result = Operation.CREATE;
                    break;
            case 1: result = Operation.ADD;
                    break;
            case 2: result = Operation.REMOVE;
                    break;
            case 3: result = Operation.EXTRACT;
                    break;
            case 4: result = Operation.CONTENT;
                    break;
            case 5: result = Operation.EXIT;
                    break;
        }

        return result;
    }
}
