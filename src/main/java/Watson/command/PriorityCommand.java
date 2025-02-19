package Watson.command;

import Watson.exception.WatsonException;
import Watson.storage.Storage;
import Watson.task.Task;
import Watson.task.TaskList;
import Watson.ui.Ui;
import Watson.task.Priority;

import java.io.IOException;

public class PriorityCommand implements Command {
    private final int taskIndex;
    private final Priority priority;

    public PriorityCommand(int taskIndex, Priority priority) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
        this.priority = priority;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new WatsonException("Invalid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.setPriority(priority);
        try {
            storage.saveTask(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ui.showMessage("Priority updated for task " + (taskIndex + 1) + " to " + priority);
    }
}