package controller;

import model.TaskList;
import utils.DuduException;
import utils.Ui;

import java.io.File;
import java.io.IOException;

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
    public void execute(TaskList tasks, File cachedTasks) throws DuduException {
        try {
            int index = Integer.parseInt(description) - 1;
            validateIndex(index, tasks);
            Ui.printContent(tasks.deleteTask(index));
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
