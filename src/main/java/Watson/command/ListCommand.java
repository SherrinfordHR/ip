package Watson.command;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTaskList(tasks.getAll());
    }
}