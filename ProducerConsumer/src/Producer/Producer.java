package Producer;

import Channel.Channel;

public interface Producer extends Runnable{
    Channel ch = null;
    public void produce() throws InterruptedException;
}
