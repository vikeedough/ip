package controller;

import model.Task;
import model.TaskList;
import utils.DuduException;
import utils.Ui;

public class MarkCommand implements Command{

    private final String description;

    public MarkCommand(String description) {
        this.description = description;
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }
    @Override
    public void execute(TaskList tasks) throws DuduException {
        int index = Integer.parseInt(description) - 1;
        validateIndex(index, tasks);
        Task curr = tasks.get(index);
        if (!curr.getIsDone()) {
            curr.toggleDone();
            String toPrint = "Nice! I've marked this task as done:\n" + curr;
            Ui.printContent(toPrint);
        } else {
            throw new DuduException("Task has already been marked as done!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
