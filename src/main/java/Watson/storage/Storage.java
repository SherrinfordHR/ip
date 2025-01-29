package Watson.storage;
import Watson.task.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> load() throws IOException {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filepath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return loadedTasks; // Return empty list for new file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseLine(line);
                if (task != null) loadedTasks.add(task);
            }
        }
        return loadedTasks;
    }

    private Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T": return parseTodo(parts);
            case "D": return parseDeadline(parts);
            case "E": return parseEvent(parts);
            default: return null;
        }
    }

    private Task parseTodo(String[] parts) {
        ToDo todo = new ToDo(parts[2]);
        todo.setstatus(parts[1]);
        return todo;
    }

    private Task parseDeadline(String[] parts) {
        Deadline deadline = new Deadline(parts[2], parts[3]);
        deadline.setstatus(parts[1]);
        return deadline;
    }

    private Task parseEvent(String[] parts) {
        Events event = new Events(parts[2], parts[3], parts[4]);
        event.setstatus(parts[1]);
        return event;
    }

    public void savetask(TaskList tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (Task task : tasks.getAll()) {
                writer.write(task.tofile());
                writer.newLine();
            }
        }
    }
}