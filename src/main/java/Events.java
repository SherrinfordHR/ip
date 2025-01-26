public class Events extends Task{
    protected String from;
    protected String to;

    public Events(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String tofile() {
        return "E | " + (status ? "1 | " : "0 | ") + super.tofile() + " | " + this.from + " | " + this.to;
    }
}