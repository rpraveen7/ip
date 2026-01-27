public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() { //each task needs its own status so cannot be static
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}

