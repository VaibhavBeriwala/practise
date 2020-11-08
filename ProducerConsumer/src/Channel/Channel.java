package Channel;

public interface Channel<T> {
    public void addToChannel(T a) throws InterruptedException;

    public T getFromChannel() throws InterruptedException;
}
