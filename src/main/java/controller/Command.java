package controller;

import model.TaskList;
import utils.DuduException;

public abstract interface Command {
    void execute(TaskList tasks) throws DuduException;
    boolean isExit();
}
