public class Message {
    private String msg;
    private String topic;

    public Message(String msg, String topic){
        this.msg = msg;
        this.topic = topic;
    }

    public String getMsg(){return this.msg;}
    public String getTopic(){return this.topic;}
}
