package veenbot;

import veenbot.commands.Command;
import veenbot.core.Parser;
import veenbot.core.Storage;
import veenbot.core.TaskManager;
import veenbot.core.Ui;
import veenbot.exceptions.VeenException;

import java.util.Scanner;

public class Veen {

    private final TaskManager taskManager;
    private final Storage storage;

    // Constructor for Veen
    public Veen() {
        taskManager = new TaskManager();
        storage = new Storage("data/veen.txt");

        try {
            storage.load(taskManager); // Load existing data on startup
        } catch (VeenException e) {
            Ui.showError(e.getMessage());
        }
    }

    // Runs the chatbot
    public void run() {
        Ui.showWelcomeMessage();
        Ui.showLoadMessage(taskManager.getSize());

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parseCommand(input);  // Parse input → Command
                command.execute(taskManager, storage);               // Execute command
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