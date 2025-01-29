package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class MarkCommand implements Command{

    private final String description;

    /**
     * Creates MarkCommand object.
     *
     * @param description Arguments of command.
     */
    public MarkCommand(String description) {
        this.description = description;
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Marks selected task as done.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @throws DuduException If task has already been marked as done.
     * @throws IOException If write operation is interrupted.
     */
    @Override
    public void execute(TaskList tasks, File cachedTasks) throws DuduException, IOException {
        int index = Integer.parseInt(description) - 1;
        validateIndex(index, tasks);
        Task curr = tasks.get(index);
        if (!curr.getIsDone()) {
            curr.toggleDone();
            String toPrint = "Nice! I've marked this task as done:\n" + curr;
            Ui.printContent(toPrint);
            FileOperation.overwriteFile(cachedTasks, tasks);
        } else {
            throw new DuduException("Task has already been marked as done!");
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
