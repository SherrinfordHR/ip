package Watson.command;

import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

/**
 * Represents an executable command for the Watson application.
 */
public interface Command {
    /**
     * Executes the command's logic.
     *
     * @param tasks The task list to operate on.
     * @param storage The storage handler for reading/writing data.
     * @param ui The UI for displaying feedback to the user.
     * @throws WatsonException If an error occurs during execution.
     */
    void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException;
}