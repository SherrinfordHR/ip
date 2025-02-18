package Watson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline. Parses and stores the due date/time.
 * Inherits from the Task class and supports deadline-specific formatting.
 */
public class Deadline extends Task {
    private final String due;
    private LocalDateTime duedate;

    /**
     * Constructs a Deadline task with a description and due date string.
     * Attempts to parse the due date using the "d/M/yyyy HHmm" format. Retains the raw string if parsing fails.
     *
     * @param description The task description.
     * @param due The due date string (e.g., "2/12/2023 1800").
     */
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.duedate = LocalDateTime.parse(due, formatter);
        } catch (DateTimeException e) {
            this.duedate = null;
        }
    }

    /**
     * Returns a formatted string for display. Uses "MMM dd yyyy, h:mm" if parsed successfully;
     * otherwise, uses the raw due string.
     *
     * @return Formatted string (e.g., "[D] [X] Submit report (by: Dec 02 2023, 6:00 PM)").
     */
    @Override
    public String toString() {
        if (this.duedate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            String newdate = this.duedate.format(formatter);
            return "[D]" + super.toString() + " (by: " + newdate + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }

    /**
     * Serializes the task for file storage in the format: "D | [status] | [description] | [due]".
     *
     * @return A string like "D | 1 | Submit report | 2/12/2023 1800".
     */
    @Override
    public String toFile() {
        return "D | " + (status ? "1 | " : "0 | ") + super.toFile() + " | " + this.due;
    }
}