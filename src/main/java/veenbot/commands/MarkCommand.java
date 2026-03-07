package veenbot.commands;
import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;
import veenbot.tasks.Task;

/**
 * Command to mark a task as completed or incomplete.
 */
public class MarkCommand extends Command {
    private final String argument;
    private final boolean isDone;  // true = mark, false = unmark

    /**
     * Constructs a MarkCommand for a specific task.
     *
     * @param argument The string representation of the task number.
     * @param isDone True to mark as done, false to unmark.
     */
    public MarkCommand(String argument, boolean isDone) {
        this.argument = argument;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws VeenException {
        int taskNumber = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;

        if (arrayIndex < 0 || arrayIndex >= taskManager.getSize()) {
            throw new VeenException("That task number doesn't exist in your list bro!");
        }

        Task task = taskManager.getTask(arrayIndex);

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }

        ui.showTaskMarked(task, isDone);
        storage.save(taskManager);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
