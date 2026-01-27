import java.util.Scanner;

public class Veen {

    private static final String Divider = "_______________________________________";
    private static final String Exit_Command = "bye";
    private static final String List_Command = "list";
    public static String[] taskList = new String[100];
    public static int totalTasks = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();


        String input = scanner.nextLine();
        while (!input.equals(Exit_Command)) {
            if(input.equals(List_Command)) {
                printTasks();
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
        System.out.println(Divider);
        System.out.println("Yo! I'm Veen");
        System.out.println("What can I do for you?");
        System.out.println(Divider);
    }

    private static void printGoodbyeMessage() {
        System.out.println(Divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Divider);
    }

    private static void echoInput(String input) {
        System.out.println(Divider);
        System.out.println("added: " + input);
        System.out.println(Divider);
    }

    private static void storeTasks(String input) {
        taskList[totalTasks] = input;
    }

    private static void printTasks() {
        System.out.println(Divider);
        for(int i = 0; i < totalTasks; i++) {
            System.out.print(i + 1 + ".");
            System.out.println(" " + taskList[i]);
        }
        System.out.println(Divider);
    }
}