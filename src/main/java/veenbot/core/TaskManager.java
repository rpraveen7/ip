package veenbot.core;
import veenbot.tasks.Task;
import veenbot.exceptions.VeenException;

// Manages task collection

public class TaskManager {
    private Task[] tasks;
    private int totalTasks;
    private static final int MAX_TASKS = 100;

    // Constructor to create an empty task manager
    public TaskManager() {
        tasks = new Task[MAX_TASKS];  // Create empty array
        totalTasks = 0;               // No tasks yet
    }

    // Adds task to the list
    // Throws exception if list full
    public void addTask(Task task) throws VeenException {
        if (totalTasks >= MAX_TASKS) {
            throw new VeenException("Task list is full!");
        }
        tasks[totalTasks] = task;
        totalTasks++;
    }

    // Gets task at specific index (0-based)
    // My own implementation
    public Task getTask(int index) throws VeenException {
        if (index < 0 || index >= totalTasks) {
            throw new VeenException("Bro, task number " + (index + 1) + " doesn't exist! You only have " + totalTasks + " tasks.");
        }
        return tasks[index];
    }

    // Able to get the total size of the task list
    public int getSize() {
        return totalTasks;
    }

    public Task[] getAllTasks() {
        Task[] filledTasks = new Task[totalTasks];
        System.arraycopy(tasks, 0, filledTasks, 0, totalTasks);
        return filledTasks;
    }

}
