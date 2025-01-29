package dudu.utils;

import java.util.Scanner;

public class Ui {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Receives input from the user.
     *
     * @return Next line inputted by the user.
     */
    public static String getInput() {
        return scanner.nextLine();
    }

    /** Prints a line. **/
    public static void printLine() {
        System.out.println("------------------------" +
                "--------------------------------");
    }

    /** Prints the list of commands available to the user. **/
    public static void printCommands() {
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
        System.out.println("Dismisses dudu.Dudu.");
        printLine();
    }

    /** Prints the Dudu logo. **/
    public static void printDudu() {
        String dudu =
                "  ____  _    _ ____  _    _\n" +
                        " |  _ \\| |  | |  _ \\| |  | |\n" +
                        " | | | | |  | | | | | |  | |\n" +
                        " | |_| | |__| | |_| | |__| |\n" +
                        " |____/ \\____/|____/ \\____/\n";
        System.out.println(dudu);
    }

    /** Prints greeting message. **/
    public static void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Dudu!");
        System.out.println("How may I assist you today?");
        printLine();
    }

    /** Prints exit message. **/
    public static void printExit() {
        printLine();
        System.out.println("See you next time!");
        printLine();
    }

    /** Prints given content without any border. **/
    public static void printContentWithoutLines(String input) {
        System.out.println(input);
    }

    /** Prints given content with borders. **/
    public static void printContent(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }
}
