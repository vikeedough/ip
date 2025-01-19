package utils;

import controller.Entry;
import controller.EntryList;

import java.util.Objects;
import java.util.Scanner;

public class Utils {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String LIST = "list";
    private static final String MARK = "mark ";
    private static final String UNMARK = "unmark ";
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

    public static void startLoop(EntryList entryList) {
        String input;
        while(true) {
            input = getInput();
            if (Objects.equals(input, BYE)) {
                printExit();
                break;
            } else if (Objects.equals(input, LIST)) {
                printLine();
                entryList.printList();
                printLine();
            } else if (input.startsWith(MARK)) {
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Entry curr = entryList.get(taskIndex - 1);
                    if (!curr.getIsDone()) {
                        curr.toggleDone();
                        String toPrint = "Nice! I've marked this task as done: \n" + curr;
                        printContent(toPrint);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please key in a valid task number!");
                }
            } else if (input.startsWith(UNMARK)) {
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Entry curr = entryList.get(taskIndex - 1);
                    if (curr.getIsDone()) {
                        curr.toggleDone();
                        String toPrint = "Alright, I've marked this task as not done yet: \n" + curr;
                        printContent(toPrint);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please key in a valid task number!");
                }
            }
            else {
                Entry entry = new Entry(input);
                printContent(entryList.addEntry(entry));

            }
        }
    }
}

