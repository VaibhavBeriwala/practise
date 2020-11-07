package Producer;

import Channel.Channel;
import java.util.Random;

public class StringProducer implements Producer{

    Channel<String> ch;
    Random r;
    boolean stop = false;

    public StringProducer(Channel<String> ch){
        this.ch = ch;
        r = new Random();
    }

    @Override
    public void produce() throws InterruptedException {
        while (!stop){
            byte[] array = new byte[7]; // length is bounded by 7
            r.nextBytes(array);
            ch.addToChannel(array.toString());
            System.out.println("Produced by StringProducer: "+ Thread.currentThread() + " : " + array);
            Thread.sleep(2000);
        }
    }

    public void stopProduce(){
        this.stop = true;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            stopProduce();
            e.printStackTrace();
        }
    }
}
