package Channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IntegerChannel implements Channel<Integer> {
    BlockingQueue<Integer> channel;

    public IntegerChannel(){
        channel = new LinkedBlockingQueue<Integer>(5);
    }

    @Override
    public void addToChannel(Integer a) {
        channel.add(a);
    }

    @Override
    public Integer getFromChannel() {
        return channel.poll();
    }
}
