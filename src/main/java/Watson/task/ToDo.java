package Watson.task;

/**
 Represents a todo task without any date/time constraints.
 Inherits from the Task class.
 */
public class ToDo extends Task{
    /**
     Constructs a ToDo task with a description.
     @param description The task description.
     */
    public ToDo(String description){
        super(description);
    }

    /**
     Returns a formatted string representation of the todo task.
     @return Formatted todo string with status and description.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     Serializes the todo task for storage in a file.
     @return A string in the format "T | [status] | [description]".
     */
    @Override
    public String tofile() {
        return "T | " + (status ? "1 | " : "0 | ") + super.tofile();
    }
}
