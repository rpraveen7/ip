package veenbot.core;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    // Tries to parse full date/time like 2/12/2019 1800
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
    // Tries to find "1800" at the end of a string like "Sunday 1800"
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
    // Helper to format the display string for ANY task
    public static String getDisplayString(String original, LocalDateTime dateTime, String timeOnly) {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        } else if (timeOnly != null) {
            return timeOnly;
        }
        return original;
    }
}
