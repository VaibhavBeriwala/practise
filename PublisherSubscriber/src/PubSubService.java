import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class PubSubService {
    private HashMap<String, Set<Consumer>> subscriberMap;
    private BlockingQueue<Message> queue;
    private ExecutorService queueProcessor;
    private int threadPoolCount;
    boolean stop = false;

    public PubSubService(int threadPoolCount){
        subscriberMap = new HashMap<>();
        queue = new LinkedBlockingQueue<>();
        this.threadPoolCount = threadPoolCount;
        queueProcessor = Executors.newFixedThreadPool(threadPoolCount);
    }

    public void start(){
        for(int i = 0; i< this.threadPoolCount; i++){
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        while(!stop){
                            processMsg(queue.take());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            queueProcessor.execute(r);
        }
    }

    public void stop(){
        queueProcessor.shutdownNow();
        stop = true;
    }

    public void subscribe(String topic, Consumer s){
        Set<Consumer> subscriberSet = subscriberMap.get(topic);
        if(subscriberSet == null){
            subscriberSet = new HashSet<Consumer>();
            subscriberMap.put(topic, subscriberSet);
        }
        subscriberSet.add(s);
    }

    public void publish(Message msg){
        queue.offer(msg);
    }

    void notifySubscriber(Consumer s, String msg){
        s.consume(msg);
    }

    void processMsg(Message msg){
        System.out.println("Kafka: Msg " + msg.getMsg() + " is being processed by : " + Thread.currentThread() );
        String topic = msg.getTopic();
        Set<Consumer> subscribers = subscriberMap.get(topic);
        if(subscribers != null){
            for(Consumer s: subscribers){
                // TODO: notifySubscriber should be async
                notifySubscriber(s, msg.getMsg());
            }
        }
    }
}
