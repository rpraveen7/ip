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
    private Ui ui;

    // Constructor for Veen
    public Veen() {
        ui = new Ui();
        taskManager = new TaskManager();
        storage = new Storage("data/veen.txt");

        try {
            storage.load(taskManager); // Load existing data on startup
        } catch (VeenException e) {
            ui.showError(e.getMessage());
        }
    }

    // Runs the chatbot
    public void run() {
        ui.showWelcomeMessage();
        ui.showLoadMessage(taskManager.getSize());

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);  // Parse input → Command
                command.execute(taskManager, ui, storage);               // Execute command
                isRunning = !command.isExit();                  // Check if exit

            } catch (VeenException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("I need a number to mark/unmark things, not words!");
            }
        }
        ui.printGoodbyeMessage();
    }

    // Main entry point
    public static void main(String[] args) {
        new Veen().run();
    }
}