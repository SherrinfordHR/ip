package Watson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String due;
    private LocalDateTime duedate;

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

    @Override
    public String toString() {
        if (this.duedate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm");
            String newdate = this.duedate.format(formatter);
            return "[D]" + super.toString() + " (by: " + newdate + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }

    @Override
    public String tofile() {
        return "D | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.due;
    }
}
