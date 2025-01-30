package Watson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 Represents a task with a deadline. Parses and stores the due date/time.
 Inherits from the Task class and adds functionality for deadline-specific operations.
 */
public class Deadline extends Task{
    private final String due;
    private LocalDateTime duedate;

    /**
     Constructs a Deadline task with a description and due date string.
     Attempts to parse the due date into a LocalDateTime object using "d/M/yyyy HHmm" format.
     If parsing fails, the raw string is retained.
     @param description The task description.
     @param due         The due date string.
     */
    public Deadline(String description, String due){
        super(description);
        this.due = due;
        try {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.duedate = LocalDateTime.parse(due, formatter);
        } catch (DateTimeException e) {
            this.duedate = null;
        }
    }

    /**
     Returns a formatted string representation of the task.
     Uses "MMM dd yyyy, h:mm" format if the due date was parsed successfully.
     Otherwise, uses the raw due string.
     @return Formatted task string with deadline.
     */
    @Override
    public String toString(){
        if (this.duedate != null) {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm");
            String newdate = this.duedate.format(formatter);
            return "[D]" + super.toString() + " (by: " + newdate + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }

    /**
     Serializes the task for storage in a file.
     @return A string in the format "D | [status] | [description] | [due]".
     */
    @Override
    public String tofile() {
        return "D | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.due;
    }
}
