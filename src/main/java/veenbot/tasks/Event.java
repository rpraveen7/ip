package veenbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = parseDate(from);
        this.toDateTime = parseDate(to);
    }

    private LocalDateTime parseDate(String input) {
        try {
            if (!input.contains(" ")) {
                return java.time.LocalDate.parse(input).atStartOfDay();
            }
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(input,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException ignored) {
                return null;
            }
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        String f = (fromDateTime != null) ?
                fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) : from;
        String t = (toDateTime != null) ?
                toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) : to;
        return "[E]" + super.toString() + " (from: " + f + " " + "to: " + t + ")";
    }
}
