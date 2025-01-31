package Watson.command;

import Watson.task.Task;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final String indexStr;

    /**
     * Constructs a DeleteCommand with the specified task index string.
     *
     * @param indexStr The string representing the 1-based index of the task to delete.
     */
    public DeleteCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Executes the deletion of the specified task.
     *
     * @param tasks The task list to modify.
     * @param storage The storage handler (not used in this command).
     * @param ui The UI to display feedback.
     * @throws WatsonException If the index is invalid, out of bounds, or not a number.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            Task task = tasks.get(index);
            tasks.delete(index);
            ui.showTaskRemoved(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new WatsonException("Task number is out of range!");
        } catch (NumberFormatException e) {
            throw new WatsonException("Please provide a valid task number!");
        }
    }
}