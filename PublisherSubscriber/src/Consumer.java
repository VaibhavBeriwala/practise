public class Consumer {
    String name;
    PubSubService msgSource;

    Consumer(String name, PubSubService msgSource){
        this.name = name;
        this.msgSource = msgSource;
    }

    public void consume(String msg){
        System.out.println("Subscriber: " + this.name + " received message: " + msg);
    }

    public void subscribe(String topic){
        msgSource.subscribe(topic, this);
    }
}
