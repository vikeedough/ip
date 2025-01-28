package controller;

import model.Deadline;
import model.Task;
import model.TaskList;

import utils.DateTimeParser;
import utils.DuduException;
import utils.Ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

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
    public void execute(TaskList tasks, File cachedTasks) throws DuduException {
        try {
            String[] deadlineParts = description.split("/by ");
            if (deadlineParts.length < 2) {
                throw new DuduException("Please enter a deadline using the /by keyword.");
            }
            validateDescription(deadlineParts[0].trim());
            if (deadlineParts[1].trim().isEmpty()) {
                throw new DuduException("Please enter a deadline using the /by keyword.");
            }

            LocalDateTime parsedDateTime = DateTimeParser.parseDateTime(deadlineParts[1].trim());

            Task deadline = new Deadline(deadlineParts[0].trim(), parsedDateTime);
            Ui.printContent(tasks.addEntry(deadline));
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
