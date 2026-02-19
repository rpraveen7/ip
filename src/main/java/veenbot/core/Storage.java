package veenbot.core;

import veenbot.exceptions.VeenException;
import veenbot.tasks.*;
import java.io.*;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // SAVING: Wipes the file and writes the current snapshot of tasks
    public void save(Task[] tasks) throws VeenException {
        try {
            File f = new File(filePath);
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs(); // Creates the 'data' folder
            }

            FileWriter fw = new FileWriter(filePath); // Overwrites by default
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new VeenException("Saving failed: " + e.getMessage());
        }
    }

    // LOADING: Reads the file and fills the TaskManager array
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