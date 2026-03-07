package veenbot.core;
import java.time.LocalDate;
import veenbot.commands.AddCommand;
import veenbot.commands.Command;
import veenbot.commands.DeleteCommand;
import veenbot.commands.ExitCommand;
import veenbot.commands.FindDateCommand;
import veenbot.commands.FindWordCommand;
import veenbot.commands.ListCommand;
import veenbot.commands.MarkCommand;
import veenbot.exceptions.VeenException;

/**
 * Interprets user input and converts it into executable Command objects.
 */
public class Parser {
    /**
     * Parses the raw user input string into a specific Command.
     * @param input The full command string entered by the user.
     * @return A Command object corresponding to the user's request.
     * @throws VeenException If the input format is invalid or the command is unknown.
     */
    public static Command parseCommand(String input) throws VeenException {
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (arguments.isEmpty()) {
                throw new VeenException("I need a number to mark things, bro!");
            }
            return new MarkCommand(arguments, true);

        case "unmark":
            if (arguments.isEmpty()) {
                throw new VeenException("I need a number to unmark things, bro!");
            }
            return new MarkCommand(arguments, false);

        case "todo":
            return new AddCommand("todo", arguments);

        case "deadline":
            return new AddCommand("deadline", arguments);

        case "event":
            return new AddCommand("event", arguments);

        case "delete":
            if (arguments.isEmpty()) {
                throw new VeenException("You gotta tell me which number to delete bro!!!");
            }
            try {
                // Convert string "3" to int 3, then subtract 1 for zero-based index
                int index = Integer.parseInt(arguments) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new VeenException("That's not a number bro!!! Try something like 'delete 1'.");
            }

        case "finddate":
            try {
                LocalDate date = LocalDate.parse(arguments.trim());
                return new FindDateCommand(date);
            } catch (java.time.format.DateTimeParseException e) {
                throw new VeenException("Bro, tell me the date in yyyy-mm-dd format (e.g., finddate 2026-03-04)");
            }

        case "find":
            if (arguments.trim().isEmpty()) {
                throw new VeenException("What am I supposed to find? Give me a keyword bro!");
            }
            return new FindWordCommand(arguments.trim());
        default:
            throw new VeenException("BROOOO!! What is thaaaat? I don't know that command!");
        }
    }
}
