package utils;

import java.util.Objects;
import java.util.Scanner;

public class Utils {

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
    }

    public static void echo(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }

    public static void receiveInput() {
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        if (!Objects.equals(input, "bye")) {
            echo(input);
            receiveInput();
        } else {
            printExit();
        }
    }

    public static void printExit() {
        printLine();
        System.out.println("See you next time!");
        printLine();
    }
}
