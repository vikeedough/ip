package controller;

import model.Deadline;
import model.Task;
import model.TaskList;

import utils.DuduException;
import utils.Ui;

public class DeadlineCommand implements Command {
    private final String description;

    public DeadlineCommand(String description) {
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
            String[] deadlineParts = description.split("/by ");
            if (deadlineParts.length < 2) {
                throw new DuduException("Please enter a deadline using the /by keyword.");
            }
            validateDescription(deadlineParts[0]);
            if (deadlineParts[1].trim().isEmpty()) {
                throw new DuduException("Please enter a deadline using the /by keyword.");
            }
            Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
            Ui.printContent(tasks.addEntry(deadline));
        } catch (DuduException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
