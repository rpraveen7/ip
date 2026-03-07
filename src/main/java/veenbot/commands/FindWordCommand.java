package veenbot.commands;
import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

/**
 * Command to find and display tasks that contain a specific keyword in their description.
 */
public class FindWordCommand extends Command {

    private final String keyword;
    /**
     * Constructs a FindWordCommand with the given keyword.
     *
     * @param keyword The string to search for.
     */
    public FindWordCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws VeenException {
        // We tell the UI to find and display tasks matching the keyword
        ui.showFoundWordTasks(taskManager, keyword);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}