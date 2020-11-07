package Consumer;

import Channel.Channel;

public interface Consumer extends Runnable{
    Channel ch = null;
    public void consume() throws InterruptedException;
}
