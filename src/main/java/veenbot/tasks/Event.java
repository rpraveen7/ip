package veenbot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import veenbot.core.DateParser;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    protected String fromTimeOnly;
    protected String toTimeOnly;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDate = DateParser.parseFullDate(from);
        this.toDate = DateParser.parseFullDate(to);
        this.fromTimeOnly = (this.fromDate == null) ? DateParser.parseTimeOnly(from) : null;
        this.toTimeOnly = (this.toDate == null) ? DateParser.parseTimeOnly(to) : null;
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        // Case 1: Start date is not a real date (e.g., "Sunday") -> Can't match
        if (fromDate == null) {
            return false;
        }
        LocalDate start = fromDate.toLocalDate();
        // Case 2: Start and End are both real dates -> Check if input is within range
        if (toDate != null) {
            LocalDate end = toDate.toLocalDate();
            return (date.isEqual(start) || date.isAfter(start)) &&
                    (date.isEqual(end) || date.isBefore(end));
        }
        // Case 3: Only Start is a real date (e.g., "to 2000") -> Just check if it matches the start day
        return date.isEqual(start);
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
    @Override
    public String toString() {
        String f = DateParser.getDisplayString(from, fromDate, fromTimeOnly);
        String t = DateParser.getDisplayString(to, toDate, toTimeOnly);
        return "[E]" + super.toString() + " (from: " + f + " to: " + t + ")";
    }
}
