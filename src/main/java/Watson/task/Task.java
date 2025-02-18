package Watson.task;

/**
 * Base class for all task types. Manages the task description and completion status.
 */
public class Task {
    private final String description;
    protected boolean status;

    /**
     * Constructs a Task with a description. Initial status is set to "not done".
     *
     * @param description The task description (e.g., "Read book").
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    /**
     * Returns the task's status icon ("[X]" for done, "[ ]" for undone).
     *
     * @return Formatted status string.
     */
    public String getStatus() {
        return status ? "[X]" : "[ ]";
    }

    /**
     * Sets the task's status based on a string input ("1" for done, "0" for undone).
     * Note: This method returns null and is intended for loading tasks from storage.
     *
     * @param s The status string ("1" or "0").
     */
    public void setStatus(String s) {
        assert s.equals("0") || s.equals("1") : "Status must be '0' or '1'";
        this.status = s.equals("1");
    }

    /**
     * Updates the task's status based on a command ("mark" or "unmark").
     *
     * @param command The action to perform ("mark" or "unmark").
     * @return Feedback message (e.g., "Nice! I've marked this task as done:").
     */
    public String updateStatus(String command) {
        if (command.equals("unmark")) {
            this.status = false;
            return "OK, I've marked this task as not done yet:";
        } else if (command.equals("mark")) {
            this.status = true;
            return "Nice! I've marked this task as done:";
        }
        return "";
    }

    /**
     * Returns a string representation of the task, including status and description.
     *
     * @return Formatted string (e.g., "[X] Read book").
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    /**
     * Serializes the task description for storage. Subclasses override this to include type and status.
     *
     * @return The task description (e.g., "Read book").
     */
    public String toFile() {
        return description;
    }

    /**
     * return the description of the task.
     *
     * @return The task description (e.g., "Read book").
     */
    public String getDescription() {
        return description;
    }
}
