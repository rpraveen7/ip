 package veenbot.commands;

import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;
import java.time.LocalDate;

public class FindDateCommand extends Command {
    private final LocalDate searchDate;
    public FindDateCommand(LocalDate searchDate) {
        this.searchDate = searchDate;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws VeenException {
        // We pass the taskManager directly to the UI
        ui.showFoundTasksOnDate(taskManager, searchDate);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
