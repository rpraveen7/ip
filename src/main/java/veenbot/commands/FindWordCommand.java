package veenbot.commands;
import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

// Command to search for tasks containing a specific keyword.
public class FindWordCommand extends Command {
    private final String keyword;

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