package veenbot.core;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Provides utility methods for parsing dates and times from strings.
 * Supports multiple formats including "yyyy-MM-dd" and "HHmm".
 */
public class DateParser {
    /**
     * Parses a full date and time string into a LocalDateTime object.
     * @param input The date/time string to parse.
     * @return The parsed LocalDateTime, or null if parsing fails.
     */
    public static LocalDateTime parseFullDate(String input) {
        String[] patterns = {"d/M/yyyy HHmm", "yyyy-MM-dd HHmm", "yyyy-MM-dd"};
        for (String pattern : patterns) {
            try {
                if (pattern.equals("yyyy-MM-dd") && !input.contains(" ")) {
                    return java.time.LocalDate.parse(input).atStartOfDay();
                }
                return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException ignored) {}
        }
        return null;
    }
    /**
     * Extracts and formats time-only strings from user input.
     * @param input The string containing a time (e.g., "1800").
     * @return A formatted time string (e.g., "6:00 PM"), or null if no time found.
     */
    public static String parseTimeOnly(String input) {
        String[] parts = input.split(" ");
        if (parts.length < 1) return null;
        String lastPart = parts[parts.length - 1];
        try {
            if (lastPart.length() == 4 && lastPart.matches("\\d+")) {
                LocalTime time = LocalTime.parse(lastPart, DateTimeFormatter.ofPattern("HHmm"));
                String formattedTime = time.format(DateTimeFormatter.ofPattern("h:mm a"));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < parts.length - 1; i++) {
                    sb.append(parts[i]).append(" ");
                }
                return sb.toString().trim() + " " + formattedTime;
            }
        } catch (DateTimeParseException ignored) {}
        return null;
    }
    /**
     * Generates a display-friendly string for a date or time.
     * @param original The original input string.
     * @param dateTime The parsed LocalDateTime object (can be null).
     * @param timeOnly The parsed time-only string (can be null).
     * @return A formatted string for UI display.
     */
    public static String getDisplayString(String original, LocalDateTime dateTime, String timeOnly) {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        } else if (timeOnly != null) {
            return timeOnly;
        }
        return original;
    }
}
