package main.java;


public class WaitNotifyBroker<T> implements Broker<T>{

    private T message;
    private boolean empty = true;

    @Override
    public synchronized T take() throws InterruptedException {
        while (empty){
            wait();
        }
        empty = true;
        notifyAll();
        Thread.interrupted()
        return message;

    }

    @Override
    public synchronized void put(T message) throws InterruptedException {
            while (!empty){
                wait();
            }
        empty = false;
        this.message = message;
        notifyAll();
    }
}
