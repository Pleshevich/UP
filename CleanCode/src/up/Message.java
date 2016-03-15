import java.text.SimpleDateFormat;
import java.util.UUID;

public class Message {
    String id;
    String author;
    String message;
    long timestamp;

    public Message(String author, String message){
        setId(UUID.randomUUID().toString());
        this.author = author;
        this.message = message;
        setTimestamp(System.currentTimeMillis());
    }

    public Message(String id, String author, long timestamp , String message){
        this.id= id;
        this.author = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getID(){
        return id;
    }

    public String getAuthor(){
        return author;
    }

    public String getMessage(){
        return message;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public String getDateInFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss" );
        return dateFormat.format(getTimestamp());
    }

    public void setId(String id){
        this.id = id;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }

    public String toString(){
        return  "[" + "{" + "\"id\"" + ":" + "\""  + id + "\"" + "," + "\"author\"" + ":" + "\""  + author + "\"" +  ","  +
                "\"timestamp\"" + ":" + "\""  + getDateInFormat() + "\"" +  "," +  "\"message\"" + ":" + "\""  + message +
                "\"" + "}" + "]";
    }

}