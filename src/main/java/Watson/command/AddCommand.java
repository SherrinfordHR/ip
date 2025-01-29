package Watson.command;

import Watson.task.Deadline;
import Watson.task.Events;
import Watson.task.Task;
import Watson.task.ToDo;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

public class AddCommand implements Command {
    private final String type;
    private final String args;

    public AddCommand(String type, String args) {
        this.type = type;
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws WatsonException {
        switch (type) {
            case "todo":
                addTodo(tasks, ui);
                break;
            case "deadline":
                addDeadline(tasks, ui);
                break;
            case "event":
                addEvent(tasks, ui);
                break;
        }
    }

    private void addTodo(TaskList tasks, Ui ui) throws WatsonException {
        if (args.isEmpty()) throw new WatsonException("ToDo cannot be empty!");
        Task task = new ToDo(args);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    private void addDeadline(TaskList tasks, Ui ui) throws WatsonException {
        String[] parts = args.split("/by");
        if (parts.length < 2) throw new WatsonException("Deadline format invalid!");
        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    private void addEvent(TaskList tasks, Ui ui) throws WatsonException {
        String[] parts = args.split("/from|/to");
        if (parts.length < 3) throw new WatsonException("Event format invalid!");
        Task task = new Events(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }
}
