package Watson.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks with operations to add, remove, retrieve, and load tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The 0-based index of the task to remove.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The 0-based index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a copy of all tasks in the list.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Appends tasks from storage to the current list.
     *
     * @param loadedTasks The list of tasks to load.
     */
    public void loadTasks(List<Task> loadedTasks) {
        tasks.addAll(loadedTasks);
    }

    public List<Task> findTasks(String keyword) {
        assert keyword != null: "Keyword must not be null";
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks){
            if(task.getdescription().toLowerCase().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        assert matchingTasks != null: "Matching tasks list should not be null";
        return matchingTasks;
    }
}