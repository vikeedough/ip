package dudu.controller;

import dudu.model.TaskList;

import dudu.utils.Ui;

import java.io.File;

public class HelpCommand implements Command {

    /**
     * Prints the list of commands available for the user.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) {
        Ui.printCommands();
    }

    /**
     * Returns false as it is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
