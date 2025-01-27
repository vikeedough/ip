package controller;

import utils.DuduException;

public class Parser {
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String HELP = "help";
    private static final String BYE = "bye";
    public static Command parse(String fullCommand) throws DuduException {
        String input = fullCommand.trim();
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String description = (parts.length > 1) ? parts[1] : "";
        description = description.trim();

        switch (command) {
        case MARK:
            return new MarkCommand(description);
        case UNMARK:
            return new UnmarkCommand(description);
        case TODO:
            return new TodoCommand(description);
        case DEADLINE:
            return new DeadlineCommand(description);
        case EVENT:
            return new EventCommand(description);
        case DELETE:
            return new DeleteCommand(description);
        case LIST:
            return new ListCommand();
        case HELP:
            return new HelpCommand();
        case BYE:
            return new ByeCommand();
        default:
            return new InvalidCommand();
        }
    }
}
