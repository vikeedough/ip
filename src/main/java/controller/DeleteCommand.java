package controller;

import model.TaskList;
import utils.DuduException;
import utils.Ui;

public class DeleteCommand implements Command {

    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks) throws DuduException {
        try {
            int index = Integer.parseInt(description) - 1;
            validateIndex(index, tasks);
            Ui.printContent(tasks.deleteTask(index));
        } catch (DuduException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
