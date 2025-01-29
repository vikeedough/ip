package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;
import dudu.model.Todo;
import dudu.model.Deadline;
import dudu.model.Event;

import dudu.utils.DateTimeParser;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileOperation {

    /**
     * Returns the local save file.
     * If no local save file is found, create a new one.
     *
     * @return Save file.
     * @throws IOException If error occurs while retrieving the file.
     */
    public static File loadFile() throws IOException {
        File file = new File("./data/duduTasks.txt");

        file.getParentFile().mkdirs();

        if (file.createNewFile()) {
            Ui.printContent("No tasks file found. Creating a new tasks file at: " + file.getAbsolutePath());
        } else {
            Ui.printContent("Cached tasks loaded.");
        }

        return file;
    }

    /**
     * Parses a line from the save file and adds it to the current list of tasks.
     *
     * @param line Line currently being read from save file.
     * @return Appropriate Task object.
     * @throws DuduException If invalid data is received from the save file.
     */
    public static Task parseFile(String line) throws DuduException {
        String[] parts = line.split("\\|");

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String task = parts[0];
        String done = parts[1];
        String name = parts[2];

        switch (task) {
        case "T":
            Todo todo = new Todo(name);
            if (done.equals("Done")) {
                todo.toggleDone();
            }
            return todo;
        case "D":
            if (parts.length < 4) {
                throw new DuduException("Invalid data for Deadline task in file.");
            }
            LocalDateTime by = DateTimeParser.parseFromFile(parts[3].trim());
            Deadline deadline = new Deadline(name, by);
            if (done.equals("Done")) {
                deadline.toggleDone();
            }
            return deadline;
        case "E":
            if (parts.length < 5) {
                throw new DuduException("Invalid data for Event task in file.");
            }
            LocalDateTime from = DateTimeParser.parseFromFile(parts[3].trim());
            LocalDateTime to = DateTimeParser.parseFromFile(parts[4].trim());
            Event event = new Event(name, from, to);
            if (done.equals("Done")) {
                event.toggleDone();
            }
            return event;
        default:
            throw new DuduException("Unknown task type in file: " + task);
        }
    }

    /**
     * Reads tasks from save file line by line.
     *
     * @param file Save file.
     * @param tasks Current list of tasks.
     * @throws FileNotFoundException If save file is not found.
     * @throws DuduException If invalid data is received from the save file.
     */
    public static void readTasksFromFile(File file, TaskList tasks) throws FileNotFoundException, DuduException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            Task task = parseFile(currLine);
            tasks.addEntry(task);
        }
        sc.close();
    }

    private static String taskToString(Task task) throws DuduException {
        String taskType;
        if (task instanceof Todo) {
            taskType = "T";
        } else if (task instanceof Deadline) {
            taskType = "D";
        } else if (task instanceof Event) {
            taskType = "E";
        } else {
            throw new DuduException("Task not recognized.");
        }

        String isDone = task.getIsDone() ? "Done" : "Not Done";

        if (task instanceof Deadline) {
            return String.format("%s | %s | %s | %s", taskType, isDone, task.getName(), ((Deadline) task).getBy());
        } else if (task instanceof Event) {
            return String.format("%s | %s | %s | %s | %s", taskType, isDone, task.getName(),
                    ((Event) task).getFrom(), ((Event) task).getTo());
        } else {
            return String.format("%s | %s | %s", taskType, isDone, task.getName());
        }
    }

    /**
     * Overwrites save file with addition, deletion or modification of tasks.
     *
     * @param file Save file.
     * @param tasks Current list of tasks.
     * @throws IOException If error occurs while writing into the save file.
     * @throws DuduException If task is not recognized.
     */
    public static void overwriteFile(File file, TaskList tasks) throws IOException, DuduException {
        FileWriter fw = new FileWriter(file, false);
        for (Task task : tasks.getAllTasks()) {
            fw.write(taskToString(task) + System.lineSeparator());
        }
        fw.close();
    }

}
