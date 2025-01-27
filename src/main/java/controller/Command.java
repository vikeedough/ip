package controller;

import model.TaskList;
import utils.DuduException;

import java.io.File;
import java.io.IOException;

public abstract interface Command {
    void execute(TaskList tasks, File cachedTasks) throws DuduException, IOException;
    boolean isExit();
}
