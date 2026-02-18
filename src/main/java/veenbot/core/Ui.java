package veenbot.core;

// This class handles all the printing and inputs

import java.util.ArrayList;

import veenbot.exceptions.VeenException;
import veenbot.tasks.Task;



public class Ui {
    // Divider message to separate inputs and outputs
    public static final String DIVIDER = "____________________________________________________________________";

    // Welcome message for Veen bot's logo
    public static void showWelcomeMessage() {
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
        System.out.println("Yo! I'm Veen");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    // Goodbye message
    public static void printGoodbyeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye bro :) Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    // Message used for when Task added
    public static void showTaskAdded(Task task, int totalTasks) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    // Message for printing of task when list command input
    public static void showTaskList(ArrayList<Task> tasks) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            // cleaner code due to overriding toString
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(DIVIDER);
    }

    // Shows a message when Task is marked/unmarked
    public static void showTaskMarked(Task task, boolean isDone) {
        System.out.println(DIVIDER);
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("Okay, I've marked this task as not done yet:");
        }
        System.out.println("  " + task);
        System.out.println(DIVIDER);
    }


    // Shows an error message
    public static void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("OOPS!!! " + message);
        System.out.println(DIVIDER);
    }

}
