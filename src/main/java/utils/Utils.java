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
    private static final String BYE = "bye";

    private static void printLine() {
        System.out.println("--------------------------------------------------------");
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

    private static void addEntry(String input, TaskList taskList) {
        Task task = new Task(input);
        printContent(taskList.addEntry(task), taskList);
    }

    private static void addTodo(String input, TaskList taskList) {
        Task todo = new Todo(input);
        printContent(taskList.addEntry(todo), taskList);
    }

    private static void addDeadline(String input, String by, TaskList taskList) {
        Task deadline = new Deadline(input, by);
        printContent(taskList.addEntry(deadline), taskList);
    }

    private static void addEvent(String input, String from, String to, TaskList taskList) {
        Task event = new Event(input, from, to);
        printContent(taskList.addEntry(event), taskList);
    }

    private static void markCommand(int index, TaskList taskList) {
        try {
            Task curr = taskList.get(index);
            if (!curr.getIsDone()) {
                curr.toggleDone();
                String toPrint = "Nice! I've marked this task as done: \n" + curr;
                printContent(toPrint);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please key in a valid task number!");
        }
    }

    private static void unmarkCommand(int index, TaskList taskList) {
        try {
            Task curr = taskList.get(index);
            if (curr.getIsDone()) {
                curr.toggleDone();
                String toPrint = "Alright, I've marked this task as not done yet: \n" + curr;
                printContent(toPrint);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please key in a valid task number!");
        }
    }

    public static void loop(TaskList taskList) {
        while (true) {
            String input = getInput().trim();
            if (input.contains(" ")) {
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String entry = parts[1];

                switch (command) {
                    case MARK:
                        int markIndex = Integer.parseInt(entry) - 1;
                        markCommand(markIndex, taskList);
                        break;
                    case UNMARK:
                        int unmarkIndex = Integer.parseInt(entry) - 1;
                        unmarkCommand(unmarkIndex, taskList);
                        break;
                    case TODO:
                        addTodo(entry, taskList);
                        break;
                    case DEADLINE:
                        String[] deadlineParts = entry.split("/by ");
                        addDeadline(deadlineParts[0], deadlineParts[1], taskList);
                        break;
                    case EVENT:
                        String[] eventParts = entry.split("/from | /to", 3);
                        addEvent(eventParts[0], eventParts[1], eventParts[2], taskList);
                        break;
                    default:
                        break;
                }
            } else {
                switch (input) {
                    case LIST:
                        printLine();
                        taskList.printList();
                        printLine();
                        break;
                    case BYE:
                        printExit();
                        return;
                    default:
                        addEntry(input, taskList);
                        break;
                }
            }
        }
    }
}

