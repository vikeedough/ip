package dudu;

import dudu.controller.Command;
import dudu.controller.FileOperation;
import dudu.controller.Parser;

import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class Dudu {

    private final TaskList tasks;

    /** Creates Dudu object. **/
    public Dudu() {
        this.tasks = new TaskList();
    }

    /**
     * Runs the main Dudu program.
     *
     * @throws DuduException If unexpected errors are thrown.
     * @throws IOException If write operations are interrupted.
     */
    public void run() throws DuduException, IOException {
        File cachedTasks = FileOperation.loadFile();
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

    public static void main(String[] args) {
        Dudu dudu = new Dudu();
        try {
            dudu.run();
        } catch (DuduException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}