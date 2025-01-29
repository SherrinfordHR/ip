package Watson.task;

public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String tofile() {
        return "T | " + (status ? "1 | " : "0 | ") + super.tofile();
    }
}
