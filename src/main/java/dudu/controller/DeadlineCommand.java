package dudu.controller;

import dudu.model.Deadline;
import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DateTimeParser;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    private final String description;

    /**
     * Creates DeadlineCommand object.
     *
     * @param description Arguments of command.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    private static void validateDescription(String input) throws DuduException {
        if (input.trim().isEmpty()) {
            throw new DuduException("The description of a task cannot be empty.");
        }
    }

    /**
     * Adds new Deadline Task to current list and overwrites save file.
     *
     * @param tasks Current Tasks in the list.
     * @param cachedTasks Save file.
     * @return String containing Deadline Task that was added.
     * @throws DuduException If no deadline is given with the /by keyword.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) throws DuduException {
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
            FileOperation.overwriteFile(cachedTasks, tasks);
            return tasks.addEntry(deadline);
        } catch (DuduException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "An error occurred.";
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
