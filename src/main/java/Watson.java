import java.util.Scanner;

public class Watson {
    public static void main(String[] args) {
        String botname = "Watson";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + botname);
        System.out.println("What can I do for you?");
        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
