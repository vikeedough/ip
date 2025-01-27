package controller;

import model.TaskList;
import utils.Ui;

import java.io.File;

public class InvalidCommand implements Command {

    @Override
    public void execute(TaskList tasks, File cachedTasks) {
        Ui.printContentWithoutLines("Invalid command. Enter 'help' for a list of commands.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
