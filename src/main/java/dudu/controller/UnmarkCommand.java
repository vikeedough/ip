package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class UnmarkCommand implements Command {
    private final String description;

    /**
     * Creates UnmarkCommand object.
     *
     * @param description Arguments of command.
     */
    public UnmarkCommand(String description) {
        this.description = description;
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Marks selected task as not done.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @throws DuduException If task has already been marked as not done.
     * @throws IOException If write operation is interrupted.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) throws DuduException, IOException {
        int index = Integer.parseInt(description) - 1;
        validateIndex(index, tasks);
        Task curr = tasks.get(index);
        if (curr.getIsDone()) {
            curr.toggleDone();
            String toPrint = "Alright, I've marked this task as not done yet:\n" + curr;
            Ui.printContent(toPrint);
            FileOperation.overwriteFile(cachedTasks, tasks);
        } else {
            throw new DuduException("Task is already marked as not done!");

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
