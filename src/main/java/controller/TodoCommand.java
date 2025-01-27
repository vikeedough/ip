package controller;

import model.Task;
import model.TaskList;
import model.Todo;
import utils.DuduException;
import utils.Ui;

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
    public void execute(TaskList tasks) throws DuduException {
        try {
            validateDescription(description);
            Task todo = new Todo(description);
            Ui.printContent(tasks.addEntry(todo));
        } catch (DuduException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
