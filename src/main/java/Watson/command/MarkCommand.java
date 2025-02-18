package Watson.command;

import Watson.task.Task;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

/**
 * Represents a command to mark or unmark a task as done/undone.
 * Validates the task index and updates its status.
 */
public class MarkCommand implements Command {
    private final String action;
    private final String indexStr;

    /**
     * Constructs a MarkCommand with the specified action and task index.
     *
     * @param action "mark" or "unmark" to indicate the desired action.
     * @param indexStr The task index (as a 1-based string) to be updated.
     */
    public MarkCommand(String action, String indexStr) {
        this.action = action;
        this.indexStr = indexStr;
    }

    /**
     * Executes the command by updating the task status and showing feedback.
     *
     * @param tasks The task list containing the target task.
     * @param storage Unused in this command (required by interface).
     * @param ui The UI component to display feedback.
     * @throws WatsonException If the index is invalid, out of bounds, or not a number.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            assert index >= 0 && index < tasks.size() : "Invalid task index";
            Task task = tasks.get(index);
            String feedback = task.updateStatus(action);
            ui.showMessage(feedback);
            ui.showMarkResult(task);
        } catch (IndexOutOfBoundsException e) {
            throw new WatsonException("Task number is out of range!");
        } catch (NumberFormatException e) {
            throw new WatsonException("Please provide a valid task number!");
        }
    }
}