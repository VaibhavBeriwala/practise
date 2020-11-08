import Channel.StringChannel;
import Channel.Channel;
import Channel.IntegerChannel;
import Consumer.StringConsumer;
import Producer.StringProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    public static void main(String args[]) throws InterruptedException {
        Channel<String> stringChannel = new StringChannel();
        //Channel<Integer> integerChannel = new IntegerChannel();

        // 3 producers, 2 consumers - stringChannel size is bounded to 5
        int producerCount = 3;
        int consumerCount = 2;
        ExecutorService producerPool = Executors.newFixedThreadPool(producerCount);
        ExecutorService consumerPool = Executors.newFixedThreadPool(consumerCount);

        for(int i=0; i<producerCount; i++){
            producerPool.execute(new StringProducer(stringChannel));
        }

        for(int i=0; i<consumerCount; i++){
            consumerPool.execute(new StringConsumer(stringChannel));
        }

        Thread.sleep(10000);

        producerPool.shutdownNow();
        consumerPool.shutdownNow();
    }
}
