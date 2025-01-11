import java.util.ArrayList;
import java.util.Scanner;

public class Watson {
    public static void main(String[] args) {
        String botname = "Watson";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> Tasklist = new ArrayList<Task>(100);

        System.out.println("Hello! I'm " + botname);
        System.out.println("What can I do for you?");
        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (command.equals("list")){
                for(int i = 0; i < Tasklist.size(); i++){
                    System.out.println(i+1 + ". " + Tasklist.get(i).getstatus() + " "
                            + Tasklist.get(i));
                }
            }
            else if (command.contains("mark")) {
                String[] words = command.split(" ");
                if (words.length == 2) {
                    int temp = Integer.parseInt(words[1]) - 1;
                    String feedback = Tasklist.get(temp).updatestatus(words[0]);
                    System.out.println(feedback);
                    System.out.println(Tasklist.get(temp).getstatus() + " "
                            + Tasklist.get(temp));
                }
                else {
                    Tasklist.add(new Task(command));
                    System.out.println("added: " + command);
                }
            }
            else {
                Tasklist.add(new Task(command));
                System.out.println("added: " + command);
            }
        }
    }
}
