import java.util.ArrayList;
import java.util.Scanner;

public class Watson {
    public static String botname = "Watson";
    public static ArrayList<Task> Tasklist = new ArrayList<>(100);

    public static void addtolist(String command) throws WatsonException{
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            if (description.isEmpty()) {
                throw new WatsonException("ToDo cannot be empty!");
            }
            System.out.println("Got it. I've added this task:");
            ToDo temp = new ToDo(description);
            Tasklist.add(temp);
            System.out.println(temp);
        }
        else if (command.startsWith("deadline")) {
            String[] temp = command.split("/by");
            String description = temp[0].substring(9).trim();
            if (description.isEmpty() || temp.length < 2) {
                throw new WatsonException("Deadline cannot be empty and must has a due date!");
            }
            System.out.println("Got it. I've added this task:");
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
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new WatsonException("Event cannot be empty and must be from time to time!");
            }
            System.out.println("Got it. I've added this task:");
            Events e = new Events(description, from, to);
            Tasklist.add(e);
            System.out.println(e);
        }
        else {
            throw new WatsonException("OOPS!!! I'm sorry, but I don't know what that means.");
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

            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    for (int i = 0; i < Tasklist.size(); i++) {
                        System.out.println(i + 1 + ". "
                                + Tasklist.get(i).toString());
                    }
                } else if ((words[0].equals("mark") || words[0].equals("unmark")) && words.length == 2) {
                    int temp = Integer.parseInt(words[1]) - 1;
                    String feedback = Tasklist.get(temp).updatestatus(words[0]);
                    System.out.println(feedback);
                    System.out.println(Tasklist.get(temp).getstatus() + " "
                            + Tasklist.get(temp));
                } else if (words[0].equals("delete") && words.length == 2) {
                    int index = Integer.parseInt((words[1])) - 1;
                    System.out.printf("Noted. I've removed this task:%n");
                    String feedback = Tasklist.get(index).toString();
                    System.out.println(feedback);
                    Tasklist.remove(index);
                    System.out.printf("Now you have %d tasks in the list.%n", Tasklist.size());
                } else {
                    addtolist(command);
                }
            } catch (WatsonException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("OOPS!" + e.getMessage());
            }

        }
        scanner.close();
    }
}
