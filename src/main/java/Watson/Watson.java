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
 Represents the main class for the Watson task management application.
 Handles initialization, loading/saving tasks, and executing user commands.
 */
public class Watson {
    private final Storage storage;
    private final TaskList Tasklist;
    private final Parser parser;
    private final Ui ui;
    /**
     Constructs a Watson instance with the specified file path for storage.
     Initializes UI, storage, task list, and parser. Loads existing tasks from storage.
     @param filepath The path to the file used for storing tasks.
     */
    public Watson(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.Tasklist = new TaskList();
        this.parser = new Parser();
        loadtask();
    }
    /**
     Loads tasks from the storage file into the task list.
     Displays an error message if loading fails.
     */
    private void loadtask() {
        try {
            Tasklist.loadTasks(storage.load());
        } catch (IOException e) {
            ui.showError("Loading tasks: " + e.getMessage());
        }
    }
    /**
     Starts the Watson application.
     Displays welcome message, processes user commands until exit, and saves tasks on each change.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String command = ui.readCommand();
            try {
                Command cmd = parser.parse(command);
                if (cmd instanceof ExitCommand) break;
                cmd.execute(Tasklist, storage, ui);
                storage.savetask(Tasklist);
            } catch (WatsonException | NumberFormatException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }

        ui.showGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new Watson("C:/Users/sherr/OneDrive/文档/ip/src/main/tasklist.txt").run();
    }
}