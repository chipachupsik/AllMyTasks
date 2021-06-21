package Archivator.command;

import Archivator.*;

public class ExitCommand implements Command {

    @Override
    public void execute() throws Exception {
        new ConsoleHelper().writeMessage("До встречи!");
    }
}
