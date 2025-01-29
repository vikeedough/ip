package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.util.ArrayList;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, File cachedTasks) throws DuduException {
        ArrayList<Task> tasksToPrint = tasks.getAllTasks();
        Ui.printLine();
        if (tasksToPrint.isEmpty()) {
            Ui.printContent("You have no tasks to show.");
        } else {
            for(int i=0; i < tasksToPrint.size(); i++) {
                Task curr = tasksToPrint.get(i);
                String result = (i + 1) + "." + curr;
                Ui.printContentWithoutLines(result);
            }
        }
        Ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
