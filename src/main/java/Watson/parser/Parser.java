package Watson.parser;

import Watson.command.*;
import Watson.exception.WatsonException;
import Watson.task.Priority;

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
                if (parts.length < 2 || parts[1].isBlank()) {
                    throw new WatsonException("Task number missing!");
                }
                return new MarkCommand(action, parts[1]);
            case "delete":
                return new DeleteCommand(parts[1]);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(action, parts.length > 1 ? parts[1] : "");
            case "find":
                return new SearchCommand(parts[1]);
            case "priority":
                if (parts.length < 2) throw new WatsonException("Priority command requires task number and priority level.");
                String[] priorityParts = parts[1].split("\\s+", 2);
                if (priorityParts.length != 2) throw new WatsonException("Format: priority <task#> <HIGH|MEDIUM|LOW>");

                try {
                    int taskNum = Integer.parseInt(priorityParts[0]);
                    Priority priority = Priority.valueOf(priorityParts[1].toUpperCase());
                    return new PriorityCommand(taskNum, priority);
                } catch (NumberFormatException e) {
                    throw new WatsonException("Invalid task number.");
                } catch (IllegalArgumentException e) {
                    throw new WatsonException("Invalid priority. Use HIGH, MEDIUM, or LOW.");
                }
            default:
                throw new WatsonException("OOPS!!! I don't know what that means.");
        }
    }
}