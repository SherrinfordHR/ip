package Watson.command;

import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand implements Command {
    /**
     * Executes the command by displaying all tasks through the UI.
     *
     * @param tasks The task list to be displayed.
     * @param storage Unused in this command (required by interface).
     * @param ui The UI component to interact with the user.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTaskList(tasks.getAll());
    }
}