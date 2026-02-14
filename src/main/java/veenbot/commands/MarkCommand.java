package veenbot.commands;

import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;
import veenbot.tasks.Task;

//Command to mark or unmark a task

public class MarkCommand extends Command {
    private String argument;
    private boolean isDone;  // true = mark, false = unmark

    // Constructor for MarkCommand
    public MarkCommand(String argument, boolean isDone) {
        this.argument = argument;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws VeenException {
        int taskNumber = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;

        Task task = taskManager.getTask(arrayIndex);

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }

        Ui.showTaskMarked(task, isDone);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
