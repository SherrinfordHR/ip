package Watson.task;

/**
 Base class for all task types. Manages task description and completion status.
 */
public class Task {
    private final String description;
    protected boolean status;

    /**
     Constructs a Task with a description and sets initial status to "not done".
     @param description The task description.
     */
    public Task(String description){
        this.description = description;
        this.status = false;
    }

    /**
     Returns the task's status icon (e.g., "[X]" for done, "[ ]" for undone).
     @return Formatted status string.
     */
    public String getstatus() {
        return status ? "[X]" : "[ ]";
    }

    /**
     Returns the task's status icon (e.g., "[X]" for done, "[ ]" for undone).
     @return Formatted status string.
     */
    public Task setstatus(String s) {
        this.status = s.equals("1");
        return null;
    }

    /**
     Updates the task's status based on a command ("mark" or "unmark").
     @param command The action to perform ("mark" or "unmark").
     @return Feedback message indicating the updated status.
     */
    public String updatestatus(String command){
        if (command.equals("unmark")) {
            this.status = false;
            return "OK, I've marked this task as not done yet:";
        }
        else if (command.equals("mark")) {
            this.status = true;
            return "Nice! I've marked this task as done:";
        }
        return "";
    }

    /**
     Returns a string representation of the task, including status and description.
     @return Formatted task string.
     */
    @Override
    public String toString() {
        return getstatus() + " " + description;
    }

    /**
     Serializes the task description for storage (without status or type).
     @return The task description.
     */
    public String tofile() {
        return description;
    }
}
