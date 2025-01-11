public class Task {
    protected String description;
    protected boolean status;

    public Task(String description){
        this.description = description;
        this.status = false;
    }

    public String getstatus() {
        return status ? "[X]" : "[ ]";
    }

    public String updatestatus(String command){
        if (command.contains("unmark")) {
            this.status = false;
            return "OK, I've marked this task as not done yet:";
        }
        else if (command.contains("mark")) {
            this.status = true;
            return "Nice! I've marked this task as done:";
        }
        return "";
    }

    @Override
    public String toString() {
        return description;
    }
}
