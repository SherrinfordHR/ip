package Watson.command;

import Watson.task.Deadline;
import Watson.task.Events;
import Watson.task.Task;
import Watson.task.ToDo;
import Watson.task.TaskList;
import Watson.storage.Storage;
import Watson.ui.Ui;
import Watson.exception.WatsonException;

/**
 * Represents a command to add a task (ToDo, Deadline, or Event) to the task list.
 */
public class AddCommand implements Command {
    private final String type;
    private final String args;

    /**
     * Constructs an AddCommand with the specified task type and arguments.
     *
     * @param type The type of task to add (e.g., "todo", "deadline", "event").
     * @param args The arguments provided for the task.
     */
    public AddCommand(String type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Executes the add command by delegating to specific methods based on the task type.
     *
     * @param tasks The task list to modify.
     * @param storage The storage handler (not used in this command).
     * @param ui The UI to display feedback.
     * @throws WatsonException If task creation fails due to invalid input format.
     */
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

    /**
     * Adds a ToDo task to the task list.
     *
     * @param tasks The task list to modify.
     * @param ui The UI to display feedback.
     * @throws WatsonException If the description is empty.
     */
    private void addTodo(TaskList tasks, Ui ui) throws WatsonException {
        if (args.isEmpty()) throw new WatsonException("ToDo cannot be empty!");
        Task task = new ToDo(args);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param tasks The task list to modify.
     * @param ui The UI to display feedback.
     * @throws WatsonException If the input format is invalid (e.g., missing "/by" delimiter).
     */
    private void addDeadline(TaskList tasks, Ui ui) throws WatsonException {
        String[] parts = args.split("/by");
        if (parts.length < 2) throw new WatsonException("Deadline format invalid!");
        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param tasks The task list to modify.
     * @param ui The UI to display feedback.
     * @throws WatsonException If the input format is invalid (e.g., missing "/from" or "/to" delimiters).
     */
    private void addEvent(TaskList tasks, Ui ui) throws WatsonException {
        String[] parts = args.split("/from|/to");
        if (parts.length < 3) throw new WatsonException("Event format invalid!");
        Task task = new Events(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }
}