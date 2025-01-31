package Watson.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public void loadTasks(List<Task> loadedTasks) {
        tasks.addAll(loadedTasks);
    }

    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks){
            if(task.getdescription().toLowerCase().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}