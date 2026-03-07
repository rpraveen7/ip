package veenbot.commands;
import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

/**
 * Command to display all tasks currently in the list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws VeenException {
        ui.showTaskList(taskManager);
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't exit
    }
}
