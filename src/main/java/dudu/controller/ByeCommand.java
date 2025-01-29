package dudu.controller;

import dudu.model.TaskList;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;

public class ByeCommand implements Command {

    /**
     * Prints goodbye message and initiates exit of program.
     *
     * @param tasks Current tasks in the list.
     * @param cachedTasks Tasks saved in the file.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) {
        Ui.printExit();
    }

    /**
     * Returns true when command is the exit command.
     *
     * @return Exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
