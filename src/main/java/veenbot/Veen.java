package veenbot;

import java.util.Scanner;

import veenbot.tasks.Deadline;
import veenbot.tasks.Event;
import veenbot.tasks.Task;
import veenbot.tasks.Todo;

public class Veen {

    // Constants to avoid magic literals
    private static final String DIVIDER = "____________________________________________________________________";
    private static final String TASKS_MESSAGE = "Here are the tasks in your list:";

    // Command Constants
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    // Application state
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int totalTasks = 0;

    // Main
    public static void main(String[] args) {
        showWelcomeMessage();
        printWelcomeMessage();
        runCommandLoop();
        printGoodbyeMessage();
    }

    // Abstraction: Method's job is to keep running and pass on to another function based on command
    private static void runCommandLoop() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isRunning = true;

        while (isRunning) {
            input = scanner.nextLine();
            isRunning = handleCommands(input);
        }
        scanner.close();
    }

    // Decides which specific function to call based on input
    private static boolean handleCommands(String input) {
        // Split the string at the first space
        String[] parts = input.split(" ", 2);

        // The first part is always the command
        String command = parts[0].toLowerCase();

        // Check if there is a second part
        String arguments = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
            case EXIT_COMMAND:
                return false;
            case LIST_COMMAND:
                printTasks();
                return true;
            case MARK_COMMAND:
                markTask(arguments);
                return true;
            case UNMARK_COMMAND:
                unmarkTask(arguments);
                return true;
            case TODO_COMMAND:
                addTaskAsTodo(arguments);
                return true;
            case DEADLINE_COMMAND:
                addTaskAsDeadline(arguments);
                return true;
            case EVENT_COMMAND:
                addTaskAsEvent(arguments);
                return true;
            default:
                throw new VeenException("BROOOO!! What is thaaaat? I don't know that command!");
            }
        } catch (VeenException e) {
            System.out.println(DIVIDER);
            System.out.println("OOPS!!! " + e.getMessage());
            System.out.println(DIVIDER);
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("OOPS!!! I need a number to mark/unmark things, not words!");
            System.out.println(DIVIDER);
        }
        return true;
    }


    // Description added for Todo task
    private static void addTaskAsTodo(String arguments) throws VeenException {
        if (isTasksFull(totalTasks)) {
            return;
        }

        // To check if user typed anything
        if (arguments.trim().isEmpty()) {
            throw new VeenException("The description of a todo cannot be empty la bro.");
        }

        tasks[totalTasks] = new Todo(arguments);
        echoTask(tasks[totalTasks]);
        totalTasks++;
    }

    // Description added for Deadline task
    private static void addTaskAsDeadline(String arguments) throws VeenException {
        if (isTasksFull(totalTasks)) {
            return;
        }

        // To check if user typed anything
        if (arguments.trim().isEmpty()) {
            throw new VeenException("The description of a deadline cannot be empty la bro.");
        }

        // Splitting the sentence into before /by and after
        String[] parts = arguments.split("/by", 2);

        // incase the user forgot the date
        if (parts.length < 2) {
            System.out.println("Error: Deadline needs /by! (e.g., deadline return book /by Sunday)");
            System.out.println(DIVIDER);
            return;
        }

        // Splitting the description of deadline and the by
        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new VeenException("The description of a deadline cannot be empty la bro.");
        }
        if (by.isEmpty()) {
            throw new VeenException("Bro, you forgot to specify WHEN the deadline is!");
        }

        tasks[totalTasks] = new Deadline(description, by);
        echoTask(tasks[totalTasks]);
        totalTasks++;

    }

    // Description added for Event task
    private static void addTaskAsEvent(String arguments) throws VeenException {
        if (isTasksFull(totalTasks)) {
            return;
        }

        // To check if user typed anything
        if (arguments.trim().isEmpty()) {
            throw new VeenException("The description of an event cannot be empty.");
        }

        // The indexOf return -1 if the word is not there
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");

        // Check if they got indicate /from and /by
        if (fromIndex == -1 || toIndex == -1) {
            throw new VeenException("An event needs BOTH a '/from' and '/to' time bro...");
        }

        // Check if the user wrote /to before /from
        if (fromIndex >= toIndex) {
            throw new VeenException("Bro, '/from' must come BEFORE '/to' in your event!");
        }

        // Splitting the description of event, from time and to time
        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();

        // Handling edge cases
        if (description.isEmpty()) {
            throw new VeenException("The description of an event cannot be empty la bro.");
        }
        if (from.isEmpty()) {
            throw new VeenException("Bro, you forgot to specify when the event starts!");
        }
        if (to.isEmpty()) {
            throw new VeenException("Bro, you forgot to specify when the event ends!");
        }

        tasks[totalTasks] = new Event(description, from, to);
        echoTask(tasks[totalTasks]);
        totalTasks++;

    }

    // Message used for when Task added
    private static void echoTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + (totalTasks + 1) + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    // Printing of task when list command input
    private static void printTasks() {
        System.out.println(DIVIDER);
        System.out.println(TASKS_MESSAGE);
        for(int i = 0; i < totalTasks; i++) {
            // cleaner code due to overriding toString
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(DIVIDER);
    }

    // Mark the Task with X
    private static void markTask(String argument) throws VeenException {
        int taskNumber  = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;
        // bound check
        if(arrayIndex < 0 || arrayIndex >= totalTasks) {
            throw new VeenException("Bro, task number" + taskNumber + " doesn't exist!");
        }

        tasks[arrayIndex].markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks[arrayIndex]);
        System.out.println(DIVIDER);
    }

    // Remove the X from the Task
    private static void unmarkTask(String argument) throws VeenException {
        int taskNumber = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;

        // bound check
        if(arrayIndex < 0 || arrayIndex >= totalTasks) {
            throw new VeenException("Bro, task number" + taskNumber + " doesn't exist!");
        }

        tasks[arrayIndex].markAsUndone();
        System.out.println(DIVIDER);
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(" " + tasks[arrayIndex]);
        System.out.println(DIVIDER);
    }

    // Welcome message spelling out "Veen"
    private static void showWelcomeMessage() {
        String logo = " __     __  ________  ________  __    __ \n"
                + "|  \\   |  \\|        \\|        \\|  \\  |  \\\n"
                + "| \\\\   | \\\\| $$$$$$$$| $$$$$$$$| $$\\ | $$\n"
                + "| $$   | $$| $$__    | $$__    | $$$\\| $$\n"
                + " \\$$\\ /  $$| $$  \\   | $$  \\   | $$$$\\ $$\n"
                + "  \\$$\\  $$ | $$$$$   | $$$$$   | $$ \\$$$$\n"
                + "   \\$$ $$  | $$_____ | $$_____ | $$  \\$$$\n"
                + "    \\$$$   | $$     \\| $$     \\| $$   \\$$\n"
                + "     \\$     \\$$$$$$$$ \\$$$$$$$$ \\$$    \\$$\n";
        System.out.print(logo);
    }

    private static void printWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Yo! I'm Veen");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void printGoodbyeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    // Function to prevent out of bounds error
    private static boolean isTasksFull(int totalTasks) {
        if (totalTasks >= tasks.length) {
            System.out.println("Task list is full!");
            return true;
        }
        return false;
    }

}