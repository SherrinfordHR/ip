import java.util.ArrayList;
import java.util.Scanner;

public class Watson {
    public static String botname = "Watson";
    public static ArrayList<Task> Tasklist = new ArrayList<>(100);

    public static void addtolist(String command) {
        System.out.println("Got it. I've added this task:");
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            ToDo temp = new ToDo(description);
            Tasklist.add(temp);
            System.out.println(temp);
        }
        else if (command.startsWith("deadline")) {
            String[] temp = command.split("/by");
            String description = temp[0].substring(9).trim();
            String due = temp[1].trim();
            Deadline d = new Deadline(description, due);
            Tasklist.add(d);
            System.out.println(d);
        }
        else if (command.startsWith("event")) {
            String[] temp = command.split("/from|/to");
            String description = temp[0].substring(6).trim();
            String from = temp[1].trim();
            String to = temp[2].trim();
            Events e = new Events(description, from, to);
            Tasklist.add(e);
            System.out.println(e);
        }
        else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.printf("Now you have %d tasks in the list.%n", Tasklist.size());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + botname);
        System.out.println("What can I do for you?");
        while(true) {
            String command = scanner.nextLine();
            String[] words = command.split(" ");
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (command.equals("list")){
                for(int i = 0; i < Tasklist.size(); i++){
                    System.out.println(i+1 + ". "
                            + Tasklist.get(i).toString());
                }
            }
            else if (words[0].equals("mark") || words[0].equals("unmark")) {
                if (words.length == 2) {
                    int temp = Integer.parseInt(words[1]) - 1;
                    String feedback = Tasklist.get(temp).updatestatus(words[0]);
                    System.out.println(feedback);
                    System.out.println(Tasklist.get(temp).getstatus() + " "
                            + Tasklist.get(temp));
                }
                else {
                    addtolist(command);
                }
            }
            else {
                addtolist(command);
            }
        }
    }
}
