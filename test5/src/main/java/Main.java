package main.java;


public class Main {
    public static void main(String[] args) {
        Broker<Integer> broker = new WaitNotifyBroker<Integer>();
        new Thread(new Producer(broker)).start();
        new Thread(new Consumer(broker)).start();
    }
}
