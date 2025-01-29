package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class FindCommand implements Command {

    private final String description;

    /**
     * Creates FindCommand object.
     *
     * @param description Arguments of command.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Finds and prints out list of tasks with given keyword.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) {
        int counter = 1;
        Ui.printLine();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getName().contains(description)) {
                if (counter == 1) {
                    Ui.printContentWithoutLines("Here are the matching tasks in your list:");
                }
                Ui.printContentWithoutLines(counter + ". " + currTask.toString());
                counter++;
            }
        }
        if (counter == 1) {
            Ui.printContentWithoutLines("No tasks found with given keyword.");
        }
        Ui.printLine();
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
