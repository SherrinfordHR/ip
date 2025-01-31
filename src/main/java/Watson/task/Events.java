package Watson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private String from;
    private String to;
    private LocalDateTime fromdate;
    private LocalDateTime todate;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.fromdate = LocalDateTime.parse(from, formatter);
        } catch (DateTimeException e) {
            this.fromdate = null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.todate = LocalDateTime.parse(to, formatter); // Changed from 'from' to 'to'
        } catch (DateTimeException e) {
            this.todate = null;
        }
    }

    @Override
    public String toString() {
        String fromtoString;
        String totoString;
        if (fromdate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            fromtoString = fromdate.format(formatter);
        } else {
            fromtoString = this.from;
        }
        if (todate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            totoString = todate.format(formatter);
        } else {
            totoString = this.to;
        }
        return "[D]" + super.toString() + " (from: " + fromtoString + " to: " + totoString + ")";
    }

    @Override
    public String tofile() {
        return "E | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.from + " | " + this.to;
    }
}