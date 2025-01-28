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

    public TodoCommand(String description) {
        this.description = description;
    }

    private static void validateDescription(String input) throws DuduException {
        if (input.trim().isEmpty()) {
            throw new DuduException("The description of a task cannot be empty.");
        }
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
