package Watson;

import Watson.command.Command;
import Watson.command.ExitCommand;
import Watson.exception.WatsonException;
import Watson.parser.Parser;
import Watson.storage.Storage;
import Watson.task.TaskList;
import Watson.ui.Ui;

import java.io.IOException;

/**
 * Main class for the Watson task management application.
 * Handles initialization, command execution loop, and graceful shutdown.
 */
public class Watson {
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructs a Watson instance with the specified file path for storage.
     * Initializes UI, storage, task list, and parser. Loads existing tasks from storage.
     *
     * @param filepath The path to the file used for storing tasks.
     */
    public Watson(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList();
        this.parser = new Parser();
        loadTask();
    }

    /**
     * Loads tasks from the storage file into the task list.
     * Displays an error message if loading fails.
     */
    private void loadTask() {
        try {
            taskList.loadTasks(storage.load());
        } catch (IOException e) {
            ui.showError("Loading tasks: " + e.getMessage());
        }
    }

    /**
     * Starts the Watson application.
     * Displays welcome message, processes user commands until exit, and saves tasks after each command.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String command = ui.readCommand();
                Command cmd = parser.parse(command);
                if (cmd instanceof ExitCommand) break;
                cmd.execute(taskList, storage, ui);
                storage.saveTask(taskList);
            } catch (WatsonException | NumberFormatException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }

        ui.showGoodbye();
        ui.close();
    }

    /**
     * Processes the user input and returns a response.
     *
     * @param input The user input command.
     * @return The response string to be displayed in the GUI.
     */
    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            if (cmd instanceof ExitCommand) {
                return "Bye. Hope to see you again soon!";
            }
            cmd.execute(taskList, storage, ui);
            storage.saveTask(taskList);
            return ui.getLastMessage();
        } catch (WatsonException | NumberFormatException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Watson("C:/Users/sherr/OneDrive/文档/ip/src/main/tasklist.txt").run();
    }
}