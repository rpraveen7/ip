import java.util.Scanner;

public class Veen {

    private static final String Divider = "_______________________________________";
    private static final String Exit_Command = "bye";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        String input = scanner.nextLine();
        while (!input.equals(Exit_Command)) {
            echoInput(input);
            input = scanner.nextLine();
        }

        printGoodbyeMessage();
        scanner.close(); //good practice
    }
    
    private static void printWelcomeMessage() {
        System.out.println(Divider);
        System.out.println("Hello! I'm Veen");
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
        System.out.println(input);
        System.out.println(Divider);
    }
}