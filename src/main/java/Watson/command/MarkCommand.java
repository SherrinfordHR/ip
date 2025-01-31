package Watson.command;

import Watson.task.Task;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

public class MarkCommand implements Command {
    private final String action;
    private final String indexStr;

    public MarkCommand(String action, String indexStr) {
        this.action = action;
        this.indexStr = indexStr;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            Task task = tasks.get(index);
            String feedback = task.updatestatus(action);
            ui.showMessage(feedback);
            ui.showMarkResult(task);
        } catch (IndexOutOfBoundsException e) {
            throw new WatsonException("Task number is out of range!");
        } catch (NumberFormatException e) {
            throw new WatsonException("Please provide a valid task number!");
        }
    }
}