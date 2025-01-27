package controller;

import model.TaskList;
import utils.DuduException;
import utils.Ui;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks) throws DuduException {
        Ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
