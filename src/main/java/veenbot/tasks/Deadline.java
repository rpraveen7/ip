package veenbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;

    // We define multiple formats we want to support
    private static final DateTimeFormatter[] FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd") // Handles date only
    };

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = parseDate(by);
    }

    private LocalDateTime parseDate(String input) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                // If it's just a date (yyyy-MM-dd), we add 00:00 as the time
                if (!input.contains(" ")) {
                    return java.time.LocalDate.parse(input).atStartOfDay();
                }
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next formatter
            }
        }
        return null; // Not a recognized date format, treated as plain String
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        String displayBy = (dateTime != null) ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) : by;
        return "[D]" + super.toString() + " (by: " + displayBy + ")";
    }
}
