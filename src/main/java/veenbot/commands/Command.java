package veenbot.commands;
import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

/**
 * Abstract base class for all executable commands in the Veen chatbot.
 * Defines the core interface for command execution and program flow control.
 */
public abstract class Command {
    /**
     * Executes the specific logic associated with the command.
     *
     * @param taskManager The TaskManager instance to operate on.
     * @param ui The Ui instance for displaying output.
     * @param storage The Storage instance for saving or loading data.
     * @throws VeenException If the command execution fails (e.g., invalid input).
     */
    public abstract void execute(TaskManager taskManager,Ui ui, Storage storage) throws VeenException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return True if the application should exit, false otherwise.
     */
    public abstract boolean isExit();
}
