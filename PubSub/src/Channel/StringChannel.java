package Channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class StringChannel implements Channel<String> {
    BlockingQueue<String> channel;

    public StringChannel(){
      channel = new LinkedBlockingQueue<String>(5);
    }

    @Override
    public void addToChannel(String s) throws InterruptedException {
        channel.put(s);
    }

    @Override
    public String getFromChannel() throws InterruptedException {
        return channel.take();
    }
}
