// src/test/java/watson/storage/StorageTest.java
package Watson.storage;

import Watson.task.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private static final String TEST_FILE = "test_tasks.txt";

    @Test
    void saveAndLoad_maintainsTaskIntegrity() throws IOException {
        // Setup
        Storage storage = new Storage(TEST_FILE);
        TaskList original = new TaskList();
        original.add(new ToDo("Test task"));
        original.add(new Deadline("Return book", "2/12/2023 1800"));

        // Test save/load cycle
        storage.saveTask(original);
        TaskList loaded = new TaskList();
        loaded.loadTasks(storage.load());

        // Verify
        assertEquals(original.size(), loaded.size());
        assertEquals(
                original.get(1).toString(),
                loaded.get(1).toString()
        );
    }
}