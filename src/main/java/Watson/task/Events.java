package Watson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 Represents an event task with start and end times.
 Parses and stores date/time for both start and end of the event.
 */
public class Events extends Task{
    private final String from;
    private final String to;
    private LocalDateTime fromdate;
    private LocalDateTime todate;

    /**
     Constructs an Events task with description, start time, and end time.
     Attempts to parse start/end times into LocalDateTime using "d/M/yyyy HHmm" format.
     If parsing fails, raw strings are retained.
     @param description The task description.
     @param from        The start time string.
     @param to          The end time string.
     */
    public Events(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
        try {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.fromdate = LocalDateTime.parse(from, formatter);
        } catch (DateTimeException e) {
            this.fromdate = null;
        }
        try {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.todate = LocalDateTime.parse(to, formatter); // Changed from 'from' to 'to'
        } catch (DateTimeException e) {
            this.todate = null;
        }
    }

    /**
     Returns a formatted string representation of the event.
     Uses "MMM dd yyyy, h:mm a" format for parsed dates.
     Falls back to raw strings if parsing failed.
     @return Formatted event string with start and end times.
     */
    @Override
    public String toString(){
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

    /**
     Serializes the event for storage in a file.
     @return A string in the format "E | [status] | [description] | [from] | [to]".
     */
    @Override
    public String tofile() {
        return "E | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.from + " | " + this.to;
    }
}