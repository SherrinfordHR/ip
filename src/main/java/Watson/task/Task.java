package Watson.task;

public class Task {
    private final String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getstatus() {
        return status ? "[X]" : "[ ]";
    }

    public Task setstatus(String s) {
        this.status = s.equals("1");
        return null;
    }

    public String updatestatus(String command) {
        if (command.equals("unmark")) {
            this.status = false;
            return "OK, I've marked this task as not done yet:";
        } else if (command.equals("mark")) {
            this.status = true;
            return "Nice! I've marked this task as done:";
        }
        return "";
    }

    @Override
    public String toString() {
        return getstatus() + " " + description;
    }

    public String tofile() {
        return description;
    }
}
