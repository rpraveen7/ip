import java.util.Scanner;

public class Veen {
    public static void main (String[] args) {

        System.out.println("_______________________________________");
        System.out.println("Hello! I'm Veen");
        System.out.println("Yo! What can I do for you?");
        System.out.println("_______________________________________");
        String exit = "bye";
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals(exit)) {
            echoInput("    " + line);
            System.out.println("_______________________________________");
            line = in.nextLine();
        }
        System.out.println("_______________________________________");
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");

    }

    public static void echoInput(String line) {
        System.out.println("_______________________________________");
        System.out.println(line);
    }
}
