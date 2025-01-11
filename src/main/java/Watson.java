import java.util.ArrayList;
import java.util.Scanner;

public class Watson {
    public static void main(String[] args) {
        String botname = "Watson";
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> Stringlist = new ArrayList<String>(100);

        System.out.println("Hello! I'm " + botname);
        System.out.println("What can I do for you?");
        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (command.equals("list")){
                for(int i = 0; i < Stringlist.size(); i++){
                    System.out.println(i+1 + ". " + Stringlist.get(i));
                }
            }
            else {
                Stringlist.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
