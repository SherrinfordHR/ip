package Watson.command;

import Watson.task.Task;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

public class DeleteCommand implements Command {
    private final String indexStr;

    public DeleteCommand(String indexStr) {
        this.indexStr = indexStr;
    }

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