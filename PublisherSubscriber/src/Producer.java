public class Producer {
    PubSubService msgSink;

    public Producer(PubSubService msgSink){
        this.msgSink = msgSink;
    }

    public void produce(Message msg){
        msgSink.publish(msg);
    }
}
