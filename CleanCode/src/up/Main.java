import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Converter m1 = new Converter();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Select an action");
            System.out.println("\r\n");
            System.out.println("№1 - Add a message");
            System.out.println("№2 - Look in chronological order");
            System.out.println("№3 - Delete message by id");
            System.out.println("№4 - Download messages from file");
            System.out.println("№5 - Save messages to file ");
            System.out.println("№6 - Search messages by author ");
            System.out.println("№7 - Search messages by keyword ");
            System.out.println("№8 - Exit");
            System.out.println("\r\n");
            int choice = new Integer(in.nextLine());
if((choice > 8 )&&(choice<1))
    System.out.println("Incorrect choice");
            switch (choice) {
                case 1:{
                    System.out.println("Enter your name: ");
                    String author = in.nextLine();
                    if(author.isEmpty()){
                        System.out.println("Author is not entered");
                        break;
                    }
                    System.out.println("Enter your message: ");
                    String message = in.nextLine();
                    m1.add(author, message);
                }
                break;

                case 2:
                    System.out.println("Your messages: ");
                    m1.printAll();
                    System.out.println("\r\n");
                    break;

                case 3:
                    System.out.println("Enter id for delete message: ");
                    String id1 = in.nextLine();
                    m1.deleteMessage(id1);
                    System.out.println("Your message has been removed!" + "\r\n");
                    break;

                case 4:
                    System.out.println("Enter name of the file: ");
                    String name = in.nextLine();
                    m1.readFromFile(name);
                    break;

                case 5:
                    System.out.println("Enter name of the file: ");
                    String name1 = in.nextLine();
                    m1.writeToFile(name1);
                    break;

                case 6:
                    System.out.println("Enter author, who you want to search by:");
                    String newAuthor = in.nextLine();
                    m1.searchByAuthor(newAuthor);
                    break;

                case 7:
                    System.out.println("Enter your keyword: ");
                    String keyword = in.nextLine();
                    m1.searchByKeyword(keyword);
                    break;

                case 8:
                    System.out.println("Bue");
                    return;

                default:
                    System.out.println("Action selected incorrectly!" + "\r\n");
            }
        }
    }
}

