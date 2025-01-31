package Watson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with start and end times. Parses and stores date/time information.
 */
public class Events extends Task {
    private final String from;
    private final String to;
    private LocalDateTime fromdate;
    private LocalDateTime todate;

    /**
     * Constructs an Events task with description, start time, and end time.
     * Parses dates using "d/M/yyyy HHmm" format. Retains raw strings if parsing fails.
     *
     * @param description The task description.
     * @param from The start time string (e.g., "3/12/2023 0900").
     * @param to The end time string (e.g., "3/12/2023 1700").
     */
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
            this.todate = LocalDateTime.parse(to, formatter);
        } catch (DateTimeException e) {
            this.todate = null;
        }
    }

    /**
     * Returns a formatted string for display. Uses "MMM dd yyyy, h:mm a" for parsed dates;
     * falls back to raw strings if parsing failed.
     *
     * @return Formatted string (e.g., "[E] [ ] Team meeting (from: Dec 03 2023, 9:00 AM to: 5:00 PM)").
     */
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

    /**
     * Serializes the event for file storage in the format: "E | [status] | [description] | [from] | [to]".
     *
     * @return A string like "E | 0 | Team meeting | 3/12/2023 0900 | 3/12/2023 1700".
     */
    @Override
    public String tofile() {
        return "E | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.from + " | " + this.to;
    }
}