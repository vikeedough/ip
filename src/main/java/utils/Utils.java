package utils;

import model.Task;
import model.TaskList;

import java.util.Objects;
import java.util.Scanner;

public class Utils {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
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
        printContent(taskList.addEntry(task));
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
                int argument = Integer.parseInt(parts[1]) - 1;

                switch (command) {
                    case MARK:
                        markCommand(argument, taskList);
                        break;
                    case UNMARK:
                        unmarkCommand(argument, taskList);
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

