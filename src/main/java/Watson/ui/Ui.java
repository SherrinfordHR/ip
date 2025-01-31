package Watson.ui;

import Watson.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Watson");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    public void showMarkResult(Task task) {
        System.out.println("Status updated:");
        System.out.println("  " + task.getstatus() + " " + task);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

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