package veenbot.core;
import veenbot.tasks.Task;
import veenbot.exceptions.VeenException;
import java.util.ArrayList;

/**
 * Manages the collection of tasks.
 * Provides methods to add, delete, and retrieve tasks while maintaining encapsulation.
 * */
public class TaskManager {
    private final ArrayList<Task> tasks;
    /**
     * Constructs an empty TaskManager.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Adds a task to the internal list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to be deleted.
     * @return The Task object that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }
    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The Task object at the given index.
     * @throws VeenException If the index is out of bounds.
     */
    public Task getTask(int index) throws VeenException {
        if (index < 0 || index >= tasks.size()) {
            throw new VeenException("Task index" + (index + 1) + " is out of bounds bro.");
        }
        return tasks.get(index);
    }
    /**
     * Returns the total number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
