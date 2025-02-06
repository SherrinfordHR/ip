package Watson.command;

import Watson.exception.WatsonException;
import Watson.storage.Storage;
import Watson.task.Task;
import Watson.task.TaskList;
import Watson.ui.Ui;

import java.util.List;

/**
 * Represents a command to search for tasks containing a specific keyword in their description.
 * Matches tasks case-insensitively and displays results through the UI.
 */
public class SearchCommand implements Command {
    private final String keyword;

    /**
     * Constructs a SearchCommand with the specified keyword.
     *
     * @param keyword The search term to match against task descriptions.
     */
    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the search operation and displays matching tasks.
     *
     * @param tasks The task list to search through.
     * @param storage Unused in this command (required by interface).
     * @param ui The UI component to display search results.
     * @throws WatsonException If an error occurs during task processing (currently unused but retained for interface consistency).
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showTasks(matchingTasks);
    }
}