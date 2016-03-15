import org.json.simple.JSONArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Pleshevich on 12.03.2016.
 */
public class Converter {
    JSONArray messages = new JSONArray();
    JSONArray messages1 = new JSONArray();

    public void add(String author, String message) {
        Message message1 = new Message(author,message);
        messages.add(message1);
        System.out.println(messages.toString());
    }

    public void add1(String id,String author,long timestamp, String message) {
        Message message1 = new Message(id,author,timestamp,message);
        messages.add(message1);
        System.out.println(messages.toString());
    }

    public void sort() {
        Collections.sort(messages, new Comparator<Message>() {
        @Override
        public int compare(Message o1, Message o2) {
            return (int) (o1.getTimestamp()-o2.getTimestamp());
        }
    });
}

    public void printAll() {
        sort();
        for (int i = 0; i < messages.size(); i++)
            System.out.println(messages.get(i).toString());
    }

    public void deleteMessage(String id) {
        boolean flag = false;
        Iterator <Message> message = messages.iterator();
        while (message.hasNext()) {
            if (message.next().getID().equals(id)){
                flag = true;
                message.remove();
            }
        }
        if(flag==false) {
            System.out.println("Message not found");
        }
}

    public void readFromFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(name));
            scanner.useDelimiter("}");
            while (scanner.hasNext()) {
                String str[] =scanner.next().split("[,:\"]");
                String str1[]=new String[8];
                int j = 0;
                for (int i = 0; i < str.length; i++) {
                    if(!(str[i].equals(""))&&!(str[i].equals("[{"))&&!(str[i].equals("{"))) {
                        str1[j]=str[i];
                        j++;
                        System.out.println(str[i]);}
                }
                if(str[0].equals("]"))
                    break;
                long num = Long.parseLong(str1[5]);
                Message message1 = new Message(str1[1],str1[3],num,str1[7]);
                messages.add(message1);
    }
        } catch (FileNotFoundException e) {
            System.out.println("Error with file! Sorry!");
        }
     }

    public void writeToFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < messages.size(); i++)
                fw.write(messages.get(i).toString());
            fw.close();
            System.out.println("Your messages saved to file!" + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByAuthor(String newAuthor) {
        sort();
        boolean flag = false;
        Iterator <Message> message =messages.iterator();
        int i = 0;
        while (message.hasNext()) {
            if(message.next().getAuthor().equals(newAuthor)) {
                System.out.println(messages.get(i).toString());
                flag = true;
            }
            i++;
        }
        if(flag==false) {
            System.out.println("Message not found");
        }
    }

    public void searchByKeyword(String keyword) {
        sort();
        boolean flag = false;
        Iterator <Message> message = messages.iterator();
        int i = 0;
        while (message.hasNext()) {
            if(message.next().getMessage().equals(keyword)) {
                System.out.println(messages.get(i).toString());
                flag = true;
            }
            i++;
        }
        if(flag==false) {
            System.out.println("Message not found");
        }
    }
}
