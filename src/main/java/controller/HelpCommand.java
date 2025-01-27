package controller;

import model.TaskList;
import utils.Ui;

public class HelpCommand implements Command {

    @Override
    public void execute(TaskList tasks) {
        Ui.printCommands();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
