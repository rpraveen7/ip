package veenbot.commands;

import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Storage storage) {
        // Nothing to do -> just exit
        // The goodbye message is handled in main
    }

    @Override
    public boolean isExit() {
        return true;  // This command exits
    }
}
