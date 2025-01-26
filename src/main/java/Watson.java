import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Watson {
    public static String botname = "Watson";
    public static ArrayList<Task> Tasklist = new ArrayList<>(100);
    private static final String filepath = "C:/Users/sherr/OneDrive/文档/ip/src/main/tasklist.txt";

    public static void savetask(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (Task task : Tasklist){
                writer.write(task.tofile());
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void loadtask(){
        File file = new File(filepath);
        if (!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating data file: " + e.getMessage());
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        ToDo Ttemp = new ToDo(parts[2]);
                        Ttemp.setstatus(parts[1]);
                        Tasklist.add(Ttemp);
                        break;
                    case "D":
                        Deadline Dtemp = new Deadline(parts[2], parts[3]);
                        Dtemp.setstatus(parts[1]);
                        Tasklist.add(Dtemp);
                        break;
                    case "E":
                        Events Etemp = new Events(parts[2], parts[3], parts[4]);
                        Etemp.setstatus(parts[1]);
                        Tasklist.add(Etemp);
                        break;
                    default:
                        System.out.println("Corrupted task entry ignored: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

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
        loadtask();
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
                    savetask();
                } else if (words[0].equals("delete") && words.length == 2) {
                    int index = Integer.parseInt((words[1])) - 1;
                    System.out.printf("Noted. I've removed this task:%n");
                    String feedback = Tasklist.get(index).toString();
                    System.out.println(feedback);
                    Tasklist.remove(index);
                    System.out.printf("Now you have %d tasks in the list.%n", Tasklist.size());
                    savetask();
                } else {
                    addtolist(command);
                    savetask();
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
