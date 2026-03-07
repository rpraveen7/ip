package veenbot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import veenbot.core.DateParser;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;
    protected String timeOnly; // Added this to make the bot detect when we input just 24h time
    /**
     * Constructs a Deadline task with the given description and deadline time.
     * @param description The description of the deadline.
     * @param by The deadline time/date string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = DateParser.parseFullDate(by);
        // If it is not a full date, check if it contains a 4 digit time
        this.timeOnly = (this.dateTime == null) ? DateParser.parseTimeOnly(by) : null;
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        // If dateTime is null, it was a plain string like "Sunday"
        return dateTime != null && dateTime.toLocalDate().equals(date);
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        String display = DateParser.getDisplayString(by, dateTime, timeOnly);
        return "[D]" + super.toString() + " (by: " + display + ")";
    }
}
