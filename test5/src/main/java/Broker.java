package main.java;


public interface Broker<T> {
    T take() throws InterruptedException;
    void put(T message) throws InterruptedException;
}
