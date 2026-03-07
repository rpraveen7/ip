package veenbot.tasks;

/**
 * Represents a "Todo" task without any specific date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

