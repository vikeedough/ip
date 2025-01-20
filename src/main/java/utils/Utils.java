package utils;

import model.*;

import java.util.Objects;
import java.util.Scanner;

public class Utils {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String HELP = "help";
    private static final String BYE = "bye";

    private static void showCommands() {
        printLine();
        System.out.println("todo <title>");
        System.out.println("Adds a ToDo Task to your list.");
        printLine();
        System.out.println("deadline <title> /by <date/time>");
        System.out.println("Adds a Deadline Task to your list.");
        printLine();
        System.out.println("event <title> /from <date/time> /to <date/time>");
        System.out.println("Adds an Event Task to your list.");
        printLine();
        System.out.println("list");
        System.out.println("Displays your current list of tasks.");
        printLine();
        System.out.println("mark <taskIndex>");
        System.out.println("Marks the indicated task as done.");
        printLine();
        System.out.println("unmark <taskIndex>");
        System.out.println("Marks the indicated task as not done.");
        printLine();
        System.out.println("help");
        System.out.println("Shows the commands available.");
        printLine();
        System.out.println("bye");
        System.out.println("Dismisses Dudu.");
        printLine();
    }

    private static void printLine() {
        System.out.println("------------------------" +
                "--------------------------------");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Dudu!");
        System.out.println("How may I assist you today?");
        printLine();
    }

    public static void printContent(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }

    public static void printContent(String input, TaskList taskList) {
        printLine();
        System.out.println(input);
        printLine();
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public static void printExit() {
        printLine();
        System.out.println("See you next time!");
        printLine();
    }

    private static void addTodo(String input, TaskList taskList) throws DuduException {
        validateDescription(input);
        Task todo = new Todo(input);
        printContent(taskList.addEntry(todo), taskList);
    }

    private static void addDeadline(String input, String by, TaskList taskList) throws DuduException {
        validateDescription(input);
        if (by.trim().isEmpty()) {
            throw new DuduException("Please enter a deadline using the /by keyword.");
        }
        Task deadline = new Deadline(input, by);
        printContent(taskList.addEntry(deadline), taskList);
    }

    private static void addEvent(String input, String from, String to, TaskList taskList) throws DuduException {
        validateDescription(input);
        if (from.trim().isEmpty() || to.trim().isEmpty()) {
            throw new DuduException("Please enter a duration for this event using the /from and /to keywords.");
        }
        Task event = new Event(input, from, to);
        printContent(taskList.addEntry(event), taskList);
    }

    private static void deleteTask(int index, TaskList taskList) throws DuduException {
        validateIndex(index, taskList);
        printContent(taskList.deleteTask(index));
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }

    private static void validateDescription(String input) throws DuduException {
        if (input.trim().isEmpty()) {
            throw new DuduException("The description of a task cannot be empty.");
        }
    }

    private static void markCommand(int index, TaskList taskList) throws DuduException {
        validateIndex(index, taskList);
        Task curr = taskList.get(index);
        if (!curr.getIsDone()) {
            curr.toggleDone();
            String toPrint = "Nice! I've marked this task as done:\n" + curr;
            printContent(toPrint);
        } else {
            throw new DuduException("Task has already been marked as done!");
        }
    }

    private static void unmarkCommand(int index, TaskList taskList) throws DuduException {
        validateIndex(index, taskList);
        Task curr = taskList.get(index);
        if (curr.getIsDone()) {
            curr.toggleDone();
            String toPrint = "Alright, I've marked this task as not done yet:\n" + curr;
            printContent(toPrint);
        } else {
            throw new DuduException("Task is already marked as not done!");
        }
    }

    public static void loop(TaskList taskList) throws DuduException {
        while (true) {
            String input = getInput().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String entry = (parts.length > 1) ? parts[1] : "";

            switch (command) {
                case MARK:
                    try {
                        int markIndex = Integer.parseInt(entry) - 1;
                        markCommand(markIndex, taskList);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid number.");
                    } catch (DuduException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case UNMARK:
                    try {
                        int unmarkIndex = Integer.parseInt(entry) - 1;
                        unmarkCommand(unmarkIndex, taskList);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid number.");
                    } catch (DuduException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case TODO:
                    try {
                        addTodo(entry, taskList);
                    } catch (DuduException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] deadlineParts = entry.split("/by ");
                        if (deadlineParts.length < 2) {
                            throw new DuduException("Please enter a deadline using the /by keyword.");
                        }
                        addDeadline(deadlineParts[0], deadlineParts[1], taskList);
                    } catch (DuduException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        String[] eventParts = entry.split("/from | /to", 3);
                        if (eventParts.length < 3) {
                            throw new DuduException("Please enter a duration for this event " +
                                    "using the /from and /to keywords.");
                        }
                        addEvent(eventParts[0], eventParts[1], eventParts[2], taskList);
                    } catch (DuduException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case LIST:
                    printLine();
                    taskList.printList();
                    printLine();
                    break;
                case DELETE:
                    try {
                        int deleteIndex = Integer.parseInt(entry) - 1;
                        deleteTask(deleteIndex, taskList);
                    } catch (DuduException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case HELP:
                    showCommands();
                    break;
                case BYE:
                    printExit();
                    return;
                default:
                    System.out.println("Invalid command. Enter 'help' for a list of commands.");
                    break;
            }
        }
    }
}


