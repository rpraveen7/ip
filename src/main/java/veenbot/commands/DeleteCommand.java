package veenbot.commands;

import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;
import veenbot.tasks.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, Storage storage) throws VeenException {
        if (index < 0 || index >= taskManager.getSize()) {
            throw new VeenException("That task number doesn't exist in your list!");
        }
        Task removed = taskManager.deleteTask(index);
        Ui.showDeleteMessage(removed, taskManager.getSize());
        storage.save(taskManager.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't exit
    }
}
