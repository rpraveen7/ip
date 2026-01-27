import java.util.Scanner;

public class Veen {

    private static final String DIVIDER = "_______________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TASKS_MESSAGE = "Here are the tasks in your list:";
    public static Task[] taskList = new Task[100];
    public static int totalTasks = 0;

    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        String input = scanner.nextLine();
        while (!input.equals(EXIT_COMMAND)) {
            if(input.equals(LIST_COMMAND)) {
                printTasks();
            } else if (input.startsWith("mark")) {
                //extract the number from the input
                String numberPart = input.substring(5);

                //convert string "number" to integer number
                int taskNumber = Integer.parseInt(numberPart);
                markTask(taskNumber);

            } else if (input.startsWith("unmark")) {
                //extract the number from the input
                String numberPart = input.substring(7);

                //convert string "number" to integer number
                int taskNumber = Integer.parseInt(numberPart);
                unmarkTask(taskNumber);

            } else {
                echoInput(input);
                storeTasks(input);
                totalTasks++;
            }
            input = scanner.nextLine();
        }

        printGoodbyeMessage();
        scanner.close(); //good practice
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

    private static void echoInput(String input) {
        System.out.println(DIVIDER);
        System.out.println("added: " + input);
        System.out.println(DIVIDER);
    }

    private static void storeTasks(String input) {
        if (totalTasks >= taskList.length) {
            System.out.println("Task list is full!");
            return;
        }
        taskList[totalTasks] = new Task(input);
    }

    private static void printTasks() {
        System.out.println(DIVIDER);
        System.out.println(TASKS_MESSAGE);
        for(int i = 0; i < totalTasks; i++) {
            System.out.println((i + 1) + "." + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        System.out.println(DIVIDER);
    }

    private static void markTask(int taskNumber) {
        int arrayIndex = taskNumber - 1;
        taskList[arrayIndex].markAsDone();

        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + taskList[arrayIndex].getStatusIcon() + "] " + taskList[arrayIndex].getDescription());
        System.out.println(DIVIDER);
    }

    private static void unmarkTask(int taskNumber) {
        int arrayIndex = taskNumber - 1;
        taskList[arrayIndex].markAsUndone();

        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [" + taskList[arrayIndex].getStatusIcon() + "] " + taskList[arrayIndex].getDescription());
        System.out.println(DIVIDER);
    }
}