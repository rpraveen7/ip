package veenbot.tasks;

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
