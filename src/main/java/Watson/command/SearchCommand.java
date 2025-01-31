package Watson.command;

import Watson.exception.WatsonException;
import Watson.storage.Storage;
import Watson.task.Task;
import Watson.task.TaskList;
import Watson.ui.Ui;

import java.util.List;

public class SearchCommand implements Command{
    String keyword;
    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException{
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showTasks(matchingTasks);
    }
}
