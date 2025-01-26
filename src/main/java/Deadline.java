public class Deadline extends Task{
    protected String due;

    public Deadline(String description, String due){
        super(description);
        this.due = due;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }

    @Override
    public String tofile() {
        return "D | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.due;
    }
}
