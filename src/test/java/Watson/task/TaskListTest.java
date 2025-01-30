// src/test/java/watson/task/TaskListTest.java
package Watson.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void add_validTask_increasesSize() {
        Task todo = new ToDo("Read book");
        taskList.add(todo);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));
    }
}