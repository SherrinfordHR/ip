package Watson.ui;

import Watson.task.Task;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all user interactions, including input reading and message display.
 * Manages the command-line interface (CLI) for the Watson application.
 */
public class Ui {
    private String lastMessage = "";
    private final Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Watson");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads and trims a user command from the CLI.
     *
     * @return The trimmed user input string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints all tasks in a numbered list format.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            lastMessage = "There are no tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
            lastMessage = sb.toString();
        }
        System.out.println(lastMessage); // 打印到控制台（可选）
    }

    /**
     * Confirms a task addition and shows the updated total task count.
     *
     * @param task The added task.
     * @param totalTasks The new total number of tasks after addition.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        lastMessage = "Got it. I've added this task:\n  " + task + "\nNow you have " + totalTasks + " tasks in the list.";
        System.out.println(lastMessage);
    }

    /**
     * Confirms a task removal and shows the updated total task count.
     *
     * @param task The removed task.
     * @param totalTasks The new total number of tasks after removal.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        lastMessage = "Noted. I've removed this task:\n  " + task + "\nNow you have " + totalTasks + " tasks in the list.";
        System.out.println(lastMessage);
    }

    /**
     * Displays the updated status of a marked/unmarked task.
     *
     * @param task The task whose status was updated.
     */
    public void showMarkResult(Task task) {
        lastMessage = "Status updated:\n  " + task.getstatus() + " " + task;
        System.out.println(lastMessage);
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        lastMessage = "Error: " + message;
        System.out.println(lastMessage);
    }

    /**
     * Prints a general informational message to the console.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        lastMessage = message;
        System.out.println(message);
    }

    /**
     * Closes the scanner to release system resources.
     * Should be called when the UI is no longer needed.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints a list of tasks.
     *
     * @param matchingTasks list of tasks to display.
     */
    public void showTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()){
            System.out.println("No match found.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < matchingTasks.size(); i++){
            System.out.println((i+1) + ". " + matchingTasks.get(i));
        }
    }

    /**
     * Returns the last message displayed by the UI.
     *
     * @return The last message.
     */
    public String getLastMessage() {
        return lastMessage;
    }
}