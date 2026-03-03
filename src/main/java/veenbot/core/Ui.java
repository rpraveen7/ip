package veenbot.core;

import java.util.Scanner;

import veenbot.tasks.Task;
import veenbot.exceptions.VeenException;

// This class handles all the printing and inputs

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    // Divider message to separate inputs and outputs
    public final String DIVIDER = "____________________________________________________________________";

    // Welcome message for Veen bot's logo
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

    // Goodbye message
    public void printGoodbyeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye bro :) Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    // Message used for when Task added
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        String taskWord = (totalTasks == 1) ? "task" : "tasks";
        System.out.println("Now you have " + totalTasks + " " + taskWord + " in the list bro.");
        System.out.println(DIVIDER);
    }

    // Message for printing of task when list command input
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

    // Shows a message when Task is marked/unmarked
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

    // Shows an error message
    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("OOPS!!! " + message);
        System.out.println(DIVIDER);
    }

    public void showDeleteMessage (Task task, int currentTotal) {
        System.out.println(DIVIDER);
        System.out.println("Noted bro :) I've removed this task:");
        System.out.println("    " + task);

        String taskWord = (currentTotal == 1) ? "task" : "tasks";

        System.out.println("Now you have " + currentTotal + " " + taskWord + " in the list bro.");
        System.out.println(DIVIDER);
    }

    // Message to show how many tasks were loaded on startup
    public void showLoadMessage(int totalTasks) {
        String taskWord = (totalTasks == 1) ? "task" : "tasks";
        System.out.println("You currently have " + totalTasks + " " + taskWord + " saved bro!");
        System.out.println(DIVIDER);
    }
}
