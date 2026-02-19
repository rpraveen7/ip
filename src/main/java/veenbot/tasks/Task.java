package veenbot.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        //each task needs its own status so cannot be static
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String toFileFormat();

    // Override toString to return the string representation of the object
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

