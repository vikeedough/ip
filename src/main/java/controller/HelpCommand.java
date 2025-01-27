package controller;

import model.TaskList;
import utils.Ui;

import java.io.File;

public class HelpCommand implements Command {

    @Override
    public void execute(TaskList tasks, File cachedTasks) {
        Ui.printCommands();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
