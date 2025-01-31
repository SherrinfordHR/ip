package Watson.parser;

import Watson.command.*;
import Watson.exception.WatsonException;

/**
 * Parses user input into executable commands.
 * Converts raw string commands to corresponding Command objects.
 */
public class Parser {
    /**
     * Parses a user input string and returns the appropriate command.
     *
     * @param command The raw user input string (case-insensitive).
     * @return A Command object corresponding to the input.
     * @throws WatsonException If the input command is unrecognized, incomplete, or improperly formatted.
     */
    public static Command parse(String command) throws WatsonException {
        String[] parts = command.split(" ", 2);
        String action = parts[0].toLowerCase();

        switch (action) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return new MarkCommand(action, parts[1]);
        case "delete":
            return new DeleteCommand(parts[1]);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(action, parts.length > 1 ? parts[1] : "");
        default:
            throw new WatsonException("OOPS!!! I don't know what that means.");
        }
    }
}