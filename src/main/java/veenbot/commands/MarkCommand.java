package veenbot.commands;

import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;
import veenbot.tasks.Task;

//Command to mark or unmark a task

public class MarkCommand extends Command {
    private final String argument;
    private final boolean isDone;  // true = mark, false = unmark

    // Constructor for MarkCommand
    public MarkCommand(String argument, boolean isDone) {
        this.argument = argument;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskManager taskManager, Storage storage) throws VeenException {
        int taskNumber = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;

        Task task = taskManager.getTask(arrayIndex);

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }

        Ui.showTaskMarked(task, isDone);
        storage.save(taskManager.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
