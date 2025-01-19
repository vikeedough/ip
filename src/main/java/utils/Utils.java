package utils;

import controller.Entry;
import controller.EntryList;

import java.util.Objects;
import java.util.Scanner;

public class Utils {

    private static final Scanner scanner = new Scanner(System.in);

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

    public static void receiveInput(EntryList entryList) {
        String input;
        while(true) {
            input = getInput();
            if (Objects.equals(input, "bye")) {
                printExit();
                break;
            } else if (Objects.equals(input, "list")) {
                printLine();
                entryList.printList();
                printLine();
            } else {
                Entry entry = new Entry(input);
                printContent(entryList.addEntry(entry));

            }
        }
    }
}

