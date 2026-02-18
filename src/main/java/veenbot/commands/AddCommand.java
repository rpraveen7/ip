package veenbot.commands;

import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;
import veenbot.tasks.Deadline;
import veenbot.tasks.Event;
import veenbot.tasks.Task;
import veenbot.tasks.Todo;

// Command to add task (Todo, Deadline or Event)

public class AddCommand extends Command {
    private String taskType;   // "todo", "deadline", or "event"
    private String arguments;

    // Constructor for AddCommand
    public AddCommand(String taskType, String arguments) {
        this.taskType = taskType;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws VeenException {
        Task task;

        switch (taskType) {
        case "todo":
            task = createTodo(arguments);
            break;
        case "deadline":
            task = createDeadline(arguments);
            break;
        case "event":
            task = createEvent(arguments);
            break;
        default:
            throw new VeenException("Unknown task type: " + taskType);
        }

        taskManager.addTask(task);
        Ui.showTaskAdded(task, taskManager.getSize());
    }

    // Creates a Todo task
    private Task createTodo(String arguments) throws VeenException {
        if (arguments.trim().isEmpty()) {
            throw new VeenException("The description of a todo cannot be empty la bro.");
        }
        return new Todo(arguments.trim());
    }

     // Creates a Deadline task
    private Task createDeadline(String arguments) throws VeenException {
        if (arguments.trim().isEmpty()) {
            throw new VeenException("The description of a deadline cannot be empty la bro.");
        }

        String[] parts = arguments.split("/by", 2);

        if (parts.length < 2) {
            throw new VeenException("Deadline needs /by! (e.g., deadline return book /by Sunday)");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new VeenException("The description of a deadline cannot be empty la bro.");
        }
        if (by.isEmpty()) {
            throw new VeenException("Bro, you forgot to specify WHEN the deadline is!");
        }

        return new Deadline(description, by);
    }

    // Creates an Event task
    private Task createEvent(String arguments) throws VeenException {
        if (arguments.trim().isEmpty()) {
            throw new VeenException("The description of an event cannot be empty.");
        }

        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new VeenException("An event needs BOTH a '/from' and '/to' time bro...");
        }

        if (fromIndex >= toIndex) {
            throw new VeenException("Bro, '/from' must come BEFORE '/to' in your event!");
        }

        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();

        if (description.isEmpty()) {
            throw new VeenException("The description of an event cannot be empty la bro.");
        }
        if (from.isEmpty()) {
            throw new VeenException("Bro, you forgot to specify when the event starts!");
        }
        if (to.isEmpty()) {
            throw new VeenException("Bro, you forgot to specify when the event ends!");
        }

        return new Event(description, from, to);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
