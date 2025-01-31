package Watson.ui;

import Watson.task.Task;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all user interactions, including input reading and message display.
 * Manages the command-line interface (CLI) for the Watson application.
 */
public class Ui {
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Confirms a task addition and shows the updated total task count.
     *
     * @param task The added task.
     * @param totalTasks The new total number of tasks after addition.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Confirms a task removal and shows the updated total task count.
     *
     * @param task The removed task.
     * @param totalTasks The new total number of tasks after removal.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Displays the updated status of a marked/unmarked task.
     *
     * @param task The task whose status was updated.
     */
    public void showMarkResult(Task task) {
        System.out.println("Status updated:");
        System.out.println("  " + task.getstatus() + " " + task);
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Prints a general informational message to the console.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner to release system resources.
     * Should be called when the UI is no longer needed.
     */
    public void close() {
        scanner.close();
    }

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
}