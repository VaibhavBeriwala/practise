package Consumer;

import Channel.Channel;

public class StringConsumer implements Consumer{
    Channel<String> ch;
    boolean stop = false;

    public StringConsumer(Channel<String> ch){
        this.ch = ch;
    }

    @Override
    public void consume() throws InterruptedException {
        while (!stop){
            String s = ch.getFromChannel();
            System.out.println("Consumed by StringConsumer: " + Thread.currentThread() + " : "+ s);
            Thread.sleep(1000);
        }
    }

    public void stopConsuming(){
        this.stop = true;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            stopConsuming();
            e.printStackTrace();
        }
    }
}
