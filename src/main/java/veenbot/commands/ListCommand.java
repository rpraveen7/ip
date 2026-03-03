package veenbot.commands;

import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        ui.showTaskList(taskManager.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't exit
    }
}
