package controller;

import model.Deadline;
import model.Event;
import model.Task;
import model.TaskList;
import utils.DuduException;
import utils.Ui;

public class EventCommand implements Command {
    private final String description;

    public EventCommand(String description) {
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
            String[] eventParts = description.split("/from | /to", 3);
            if (eventParts.length < 3) {
                throw new DuduException("Please enter a duration for this event " +
                        "using the /from and /to keywords.");
            }
            validateDescription(eventParts[0]);
            if (eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
                throw new DuduException("Please enter a duration for this event using the /from and /to keywords.");
            }
            Task event = new Event(eventParts[0], eventParts[1], eventParts[2]);
            Ui.printContent(tasks.addEntry(event));
        } catch (DuduException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
