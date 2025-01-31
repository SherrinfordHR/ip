package Watson.command;

import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

public interface Command {
    void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException;
}