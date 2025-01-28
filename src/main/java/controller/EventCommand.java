package controller;

import model.Deadline;
import model.Event;
import model.Task;
import model.TaskList;
import utils.DateTimeParser;
import utils.DuduException;
import utils.Ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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
    public void execute(TaskList tasks, File cachedTasks) throws DuduException {
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

            LocalDateTime parsedFrom = DateTimeParser.parseDateTime(eventParts[1].trim());
            LocalDateTime parsedTo = DateTimeParser.parseDateTime(eventParts[2].trim());

            Task event = new Event(eventParts[0].trim(), parsedFrom, parsedTo);
            Ui.printContent(tasks.addEntry(event));
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
