import controller.Command;
import controller.FileOperation;
import controller.Parser;

import model.TaskList;

import utils.DuduException;
import utils.Ui;

import java.io.File;
import java.io.IOException;

public class Dudu {
    public static void main(String[] args) throws DuduException, IOException {
        File cachedTasks = FileOperation.loadFile();
        TaskList tasks = new TaskList();

        FileOperation.readTasksFromFile(cachedTasks, tasks);

        Ui.printDudu();
        Ui.printGreeting();

        while (true) {
            try {
                String fullCommand = Ui.getInput();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, cachedTasks);
                if (command.isExit()) {
                    return;
                }
            } catch (DuduException e) {
                Ui.printContentWithoutLines(e.getMessage());
            }
        }
    }
}