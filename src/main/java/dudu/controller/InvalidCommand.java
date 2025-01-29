package dudu.controller;

import dudu.model.TaskList;
import dudu.utils.Ui;

import java.io.File;

public class InvalidCommand implements Command {

    /**
     * Prints invalid command message and advises user to use the 'help' command.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) {
        Ui.printContentWithoutLines("Invalid command. Enter 'help' for a list of commands.");
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
