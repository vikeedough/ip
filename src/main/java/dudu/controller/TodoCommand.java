package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;
import dudu.model.Todo;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class TodoCommand implements Command {

    private final String description;

    /**
     * Creates TodoCommand object.
     *
     * @param description Arguments of command.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    private static void validateDescription(String input) throws DuduException {
        if (input.trim().isEmpty()) {
            throw new DuduException("The description of a task cannot be empty.");
        }
    }

    /**
     * Adds new Todo Task to current list and overwrites save file.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @throws DuduException If any unexpected error occurs.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) throws DuduException {
        try {
            validateDescription(description);
            Task todo = new Todo(description.trim());
            Ui.printContent(tasks.addEntry(todo));
            FileOperation.overwriteFile(cachedTasks, tasks);
        } catch (DuduException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
