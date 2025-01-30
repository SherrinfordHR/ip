package Watson.command;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
/**

 Represents a command to exit the Watson application.
 */
public class ExitCommand implements Command {
    /**
    Executes the exit command. No operation is performed here.
    @param tasks The task list (unused).
    @param storage The storage handler (unused).
    @param ui The UI (unused).
    */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
    }
}