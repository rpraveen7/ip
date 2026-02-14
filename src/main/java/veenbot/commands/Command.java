package veenbot.commands;

import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

public abstract class Command {

    // Executes the command
    // Task manager to operate on the UI to display results and throw exception if command fails
    public abstract void execute(TaskManager taskManager, Ui ui) throws VeenException;

    // Checks if this command will exit the program.
    public abstract boolean isExit();
}
