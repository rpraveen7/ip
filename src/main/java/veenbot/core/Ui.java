package veenbot.core;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import veenbot.tasks.Task;
import veenbot.exceptions.VeenException;

/**
 * Handles all user interactions for the Veen chatbot.
 * Responsible for reading input and displaying formatted output messages to the console.
 */
public class Ui {

    private final Scanner scanner;
    /**
     * Constructs a Ui instance and initializes the system scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Reads the next line of input from the user.
     *
     * @return The raw string input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    // Divider message to separate inputs and outputs
    public final String DIVIDER = "____________________________________________________________________";

    /**
     * Displays the welcome logo and initial greeting messages.
     */
    public void showWelcomeMessage() {
        String logo = " __     __  ________  ________  __    __\n"
                + "|  \\   |  \\|        \\|        \\|  \\  |  \\\n"
                + "| \\\\   | \\\\| $$$$$$$$| $$$$$$$$| $$\\ | $$\n"
                + "| $$   | $$| $$__    | $$__    | $$$\\| $$\n"
                + " \\$$\\ /  $$| $$  \\   | $$  \\   | $$$$\\ $$\n"
                + "  \\$$\\  $$ | $$$$$   | $$$$$   | $$ \\$$$$\n"
                + "   \\$$ $$  | $$_____ | $$_____ | $$  \\$$$\n"
                + "    \\$$$   | $$     \\| $$     \\| $$   \\$$\n"
                + "     \\$     \\$$$$$$$$ \\$$$$$$$$ \\$$    \\$$\n";
        System.out.print(logo);
        System.out.println(DIVIDER);
        System.out.println("Yo bro! I'm Veen");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Displays the goodbye message when the user exits.
     */
    public void printGoodbyeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye bro :) Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
    /**
     * Displays a confirmation message after a task is successfully added.
     *
     * @param task The task that was added.
     * @param totalTasks The new total count of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        String taskWord = (totalTasks == 1) ? "task" : "tasks";
        System.out.println("Now you have " + totalTasks + " " + taskWord + " in the list bro.");
        System.out.println(DIVIDER);
    }
    /**
     * Prints all tasks currently stored in the TaskManager.
     *
     * @param taskManager The TaskManager containing the tasks to display.
     * @throws VeenException If an error occurs while retrieving tasks.
     */
    public void showTaskList(TaskManager taskManager) throws VeenException {
        System.out.println(DIVIDER);

        //Check if the list is empty before printing
        if (taskManager.getSize() == 0) {
            System.out.println("Solid bro!! You have no tasks left!!");
        } else {
            System.out.println("Here are the tasks in your list bro:");
            for (int i = 0; i < taskManager.getSize(); i++) {
                // cleaner code due to overriding toString
                System.out.println((i + 1) + "." + taskManager.getTask(i));
            }
        }
        System.out.println(DIVIDER);
    }
    /**
     * Displays a message confirming that a task has been marked or unmarked.
     *
     * @param task The task that was modified.
     * @param isDone True if the task was marked as done, false otherwise.
     */
    public void showTaskMarked(Task task, boolean isDone) {
        System.out.println(DIVIDER);
        if (isDone) {
            System.out.println("Nice! I've marked this task as done bro:");
        } else {
            System.out.println("Okay, I've marked this task as not done yet bro:");
        }
        System.out.println("  " + task);
        System.out.println(DIVIDER);
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The specific error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("OOPS!!! " + message);
        System.out.println(DIVIDER);
    }
    /**
     * Displays a confirmation message after a task is deleted.
     *
     * @param task The task that was removed.
     * @param currentTotal The remaining number of tasks in the list.
     */
    public void showDeleteMessage (Task task, int currentTotal) {
        System.out.println(DIVIDER);
        System.out.println("Noted bro :) I've removed this task:");
        System.out.println("    " + task);

        String taskWord = (currentTotal == 1) ? "task" : "tasks";

        System.out.println("Now you have " + currentTotal + " " + taskWord + " in the list bro.");
        System.out.println(DIVIDER);
    }
    /**
     * Displays a message showing how many tasks were loaded from storage.
     *
     * @param totalTasks The number of tasks loaded.
     */
    public void showLoadMessage(int totalTasks) {
        String taskWord = (totalTasks == 1) ? "task" : "tasks";
        System.out.println("You currently have " + totalTasks + " " + taskWord + " saved bro!");
        System.out.println(DIVIDER);
    }
    /**
     * Displays all tasks that occur on a specific date.
     *
     * @param taskManager The TaskManager to search.
     * @param date The date to filter tasks by.
     * @throws VeenException If an error occurs while retrieving tasks.
     */
    public void showFoundTasksOnDate(TaskManager taskManager, LocalDate date) throws VeenException {
        System.out.println(DIVIDER);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        boolean found = false;
        int count = 1;
        for (int i = 0; i < taskManager.getSize(); i++) {
            Task task = taskManager.getTask(i);
            if (task.isOnDate(date)) {
                if (!found) {
                    System.out.println("Here are the tasks happening on " + formattedDate + " bro:");
                    found = true;
                }
                System.out.println(count + "." + task);
                count++;
            }
        }
        if (!found) {
            System.out.println("Solid bro!! You have no tasks on " + formattedDate + "!!");
        }
        System.out.println(DIVIDER);
    }
    /**
     * Displays all tasks that contain a specific keyword in their description.
     *
     * @param taskManager The TaskManager to search.
     * @param keyword The string to search for within task descriptions.
     * @throws VeenException If an error occurs while retrieving tasks.
     */
    public void showFoundWordTasks(TaskManager taskManager, String keyword) throws VeenException {
        System.out.println(DIVIDER);
        boolean isFound = false;
        int displayIndex = 1;
             for (int i = 0; i < taskManager.getSize(); i++) {
                 Task task = taskManager.getTask(i);
                 // We check if the task description contains the keyword (case-insensitive search is better)
                 if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                     if (!isFound) {
                         System.out.println("Here are the matching tasks in your list bro:");
                         isFound = true;
                     }
                     System.out.println(displayIndex + "." + task);
                     displayIndex++;
                 }
             }if (!isFound) {
                 System.out.println("I couldn't find any tasks with '" + keyword + "' bro.");
             }System.out.println(DIVIDER);
    }
}
