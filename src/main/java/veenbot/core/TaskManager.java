package veenbot.core;
import veenbot.tasks.Task;
import veenbot.exceptions.VeenException;
import java.util.ArrayList;

import javax.lang.model.type.ArrayType;

// Manages task collection

public class TaskManager {
    private final ArrayList<Task> tasks;

    // Constructor to create an empty task manager
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Adds task to the list
    public void addTask(Task task) throws VeenException {
        tasks.add(task);
    }

    // Gets task at specific index (0-based)
    public Task getTask(int index) throws VeenException {
        return tasks.get(index);
    }

    // Able to get the total size of the task list
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
