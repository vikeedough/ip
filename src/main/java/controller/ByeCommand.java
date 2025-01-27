package controller;

import model.TaskList;
import utils.DuduException;
import utils.Ui;

import java.io.File;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, File cachedTasks) throws DuduException {
        Ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
