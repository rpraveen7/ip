import java.util.Scanner;

public class Veen {

    // Constants to avoid magic literals
    private static final String DIVIDER = "_______________________________________";
    private static final String TASKS_MESSAGE = "Here are the tasks in your list:";

    // Command Constants
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";

    // Application state
    private static final int MAX_TASKS = 100;
    public static Task[] taskList = new Task[MAX_TASKS];
    public static int totalTasks = 0;

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

        while(isRunning) {
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
        String command = parts[0];

        // Check if there is a second part
        String arguments = parts.length > 1 ? parts[1] : "";

        switch(command) {
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
        default:
            // if none of the above, its a new task
            addTask(input);
            return true;
        }
    }

    private static void addTask(String description) {
        if (totalTasks >= taskList.length) {
            System.out.println("Task list is full!");
            return;
        }

        taskList[totalTasks] = new Task(description);
        echoTask(taskList[totalTasks]);
        totalTasks++;
    }

    private static void echoTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("added: " + task.getDescription());
        System.out.println(DIVIDER);
    }

    private static void printTasks() {
        System.out.println(DIVIDER);
        System.out.println(TASKS_MESSAGE);
        for(int i = 0; i < totalTasks; i++) {
            // cleaner code due to overriding toString
            System.out.println((i + 1) + "." + taskList[i]);
        }
        System.out.println(DIVIDER);
    }

    private static void markTask(String argument) {
        int taskNumber  = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;

        taskList[arrayIndex].markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + taskList[arrayIndex]);
        System.out.println(DIVIDER);
    }

    private static void unmarkTask(String argument) {
        int taskNumber = Integer.parseInt(argument);
        int arrayIndex = taskNumber - 1;

        taskList[arrayIndex].markAsUndone();
        System.out.println(DIVIDER);
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(" " + taskList[arrayIndex]);
        System.out.println(DIVIDER);
    }

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

}