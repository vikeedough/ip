package dudu.controller;

import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

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
