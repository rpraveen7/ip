package veenbot.core;

import veenbot.commands.AddCommand;
import veenbot.commands.Command;
import veenbot.commands.DeleteCommand;
import veenbot.commands.ExitCommand;
import veenbot.commands.ListCommand;
import veenbot.commands.MarkCommand;
import veenbot.exceptions.VeenException;

// Parses converts user strings into command objects

public class Parser {

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

        default:
            throw new VeenException("BROOOO!! What is thaaaat? I don't know that command!");
        }
    }
}
