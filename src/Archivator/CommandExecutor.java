package Archivator;

import Archivator.command.*;

import java.util.*;

//класс хранящий множество объектов, различных классов
//от выбора операции зависит то, объект какого класса будет создан
public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<Operation, Command>() {{
        put(Operation.ADD, new ZipAddCommand());
        put(Operation.CONTENT, new ZipContentCommand());
        put(Operation.CREATE, new ZipCreateCommand());
        put(Operation.EXIT, new ExitCommand());
        put(Operation.EXTRACT, new ZipExtractCommand());
        put(Operation.REMOVE, new ZipRemoveCommand());
    }};

    private CommandExecutor() {
    }

    //метод, вызывающий объект класса, соответствующего введёной пользователем операции и вызывающий у найденного объекта одноимённый метод
    public static void execute(Operation operation) throws Exception {
        allKnownCommandsMap.get(operation).execute();
    }
}
