package veenbot;

import veenbot.commands.Command;
import veenbot.core.Parser;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

import java.util.Scanner;

public class Veen {

    private final TaskManager taskManager;
    private final Ui ui;

    // Constructor for Veen
    public Veen() {
        taskManager = new TaskManager();
        ui = new Ui();
    }

    // Runs the chatbot
    public void run() {
        Ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parseCommand(input);  // Parse input → Command
                command.execute(taskManager, ui);               // Execute command
                isRunning = !command.isExit();                  // Check if exit

            } catch (VeenException e) {
                Ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                Ui.showError("I need a number to mark/unmark things, not words!");
            }
        }

        scanner.close();
        Ui.printGoodbyeMessage();
    }

    // Main entry point
    public static void main(String[] args) {
        new Veen().run();
    }
}