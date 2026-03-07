package veenbot.core;
import veenbot.exceptions.VeenException;
import veenbot.tasks.*;
import java.io.*;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a local file.
 * Ensures data persistence between application sessions.
 */
public class Storage {
    private final String filePath;
    /**
     * Constructs a Storage instance with the specified file path.
     * @param filePath The path to the file where tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Saves the current list of tasks to the storage file.
     * @param taskManager The TaskManager containing the tasks to save.
     * @throws VeenException If an error occurs during the saving process.
     */
    public void save(TaskManager taskManager) throws VeenException {
        try {
            File f = new File(filePath);
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs(); // Creates the 'data' folder
            }

            FileWriter fw = new FileWriter(filePath); // Overwrites by default
            for (int i = 0; i < taskManager.getSize(); i++) {
                Task task = taskManager.getTask(i);
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new VeenException("Saving failed: " + e.getMessage());
        }
    }
    /**
     * Loads tasks from the storage file into the TaskManager.
     * @param taskManager The TaskManager to load tasks into.
     * @throws VeenException If the file is not found or corrupted.
     */
    public void load(TaskManager taskManager) throws VeenException {
        File f = new File(filePath);
        if (!f.exists()) return;

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String[] parts = s.nextLine().split(" \\| ");
                Task task = null;
                switch (parts[0]) {
                case "T": task = new Todo(parts[2]); break;
                case "D": task = new Deadline(parts[2], parts[3]); break;
                case "E": task = new Event(parts[2], parts[3], parts[4]); break;
                }
                if (task != null) {
                    if (parts[1].equals("1")) task.markAsDone();
                    taskManager.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new VeenException("File not found.");
        }
    }
}