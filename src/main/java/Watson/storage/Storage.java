package Watson.storage;

import Watson.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages persistent storage for tasks by reading from and writing to a file.
 * Handles file creation, data parsing, and task serialization.
 */
public class Storage {
    private final String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The path to the file used for data storage.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the storage file. Creates the file and parent directories if they do not exist.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
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

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line The line read from the file, formatted as "TYPE | STATUS | DESCRIPTION | [EXTRA FIELDS]".
     * @return A Task object, or null if the line format is invalid.
     */
    private Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            return parseTodo(parts);
        case "D":
            return parseDeadline(parts);
        case "E":
            return parseEvent(parts);
        default:
            return null;
        }
    }

    // Private helper methods (no Javadoc needed for brevity)
    private Task parseTodo(String[] parts) {
        int descIndex = 3;
        Priority priority = Priority.MEDIUM;
        if (parts.length > 3) {
            priority = Priority.valueOf(parts[2]);
            descIndex = 3;
        } else {
            descIndex = 2;
        }
        ToDo todo = new ToDo(parts[descIndex]);
        todo.setStatus(parts[1]);
        todo.setPriority(priority);
        return todo;
    }


    private Task parseDeadline(String[] parts) {
        int descIndex = 3;
        Priority priority = Priority.MEDIUM;
        if (parts.length > 4) {
            priority = Priority.valueOf(parts[2]);
            descIndex = 3;
        } else {
            descIndex = 2;
        }
        Deadline deadline = new Deadline(parts[descIndex], parts[descIndex + 1]);
        deadline.setStatus(parts[1]);
        deadline.setPriority(priority);
        return deadline;
    }

    private Task parseEvent(String[] parts) {
        int descIndex = 3;
        Priority priority = Priority.MEDIUM;
        if (parts.length > 5) {
            priority = Priority.valueOf(parts[2]);
            descIndex = 3;
        } else {
            descIndex = 2;
        }
        Events event = new Events(parts[descIndex], parts[descIndex + 1], parts[descIndex + 2]);
        event.setStatus(parts[1]);
        event.setPriority(priority);
        return event;
    }

    /**
     * Saves all tasks from the task list to the storage file.
     *
     * @param tasks The task list to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTask(TaskList tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (Task task : tasks.getAll()) {
                writer.write(task.toFile());
                writer.newLine();
            }
        }
    }
}