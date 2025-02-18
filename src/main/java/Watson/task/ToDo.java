package Watson.task;

/**
 * Represents a todo task without date/time constraints. Inherits from Task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description.
     *
     * @param description The task description (e.g., "Buy milk").
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string for display.
     *
     * @return Formatted string (e.g., "[T] [ ] Buy milk").
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Serializes the todo task for file storage in the format: "T | [status] | [description]".
     *
     * @return A string like "T | 0 | Buy milk".
     */
    @Override
    public String toFile() {
        return "T | " + (status ? "1 | " : "0 | ") + super.toFile();
    }
}