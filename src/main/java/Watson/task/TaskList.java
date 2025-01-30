package Watson.task;

import java.util.ArrayList;
import java.util.List;

/**
 Manages a list of tasks, providing methods for adding, removing, and accessing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     Adds a task to the list.
     @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     Removes a task from the list by index.
     @param index The index of the task to remove.
     @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     Removes a task from the list by index.
     @param index The index of the task to remove.
     @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     Returns a copy of all tasks in the list.
     @return A list containing all tasks.
     */
    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    /**
     Returns the number of tasks in the list.
     @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     Loads tasks from a persisted storage into the task list.
     @param loadedTasks The list of tasks to load.
     */
    public void loadTasks(List<Task> loadedTasks) {
        tasks.addAll(loadedTasks);
    }
}