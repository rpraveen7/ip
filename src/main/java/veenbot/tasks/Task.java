package veenbot.tasks;
import java.time.LocalDate;

/**
 * Represents a generic task in the Veen chatbot.
 * This is an abstract class that provides common functionality for all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a Task with the given description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the description of the task.
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns the status icon based on whether the task is done.
     * @return "X" if done, otherwise a space.
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }
    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        //each task needs its own status so cannot be static
        this.isDone = true;
    }
    /**
     * Marks the task as not completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    /**
     * Returns the task in a formatted string suitable for file storage.
     * @return The file-formatted task string.
     */
    public abstract String toFileFormat();
    /**
     * Checks if the task occurs on a specific date.
     * @param date The date to check.
     * @return True if the task occurs on the date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    // Override toString to return the string representation of the object
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

