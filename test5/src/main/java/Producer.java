package main.java;


public class Producer implements Runnable{

    final private Broker<Integer> broker;

    public Producer(Broker<Integer> broker) {
        this.broker = broker;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                broker.put(i);
                System.out.format("produced %s%n", i);
                Thread.sleep(1000);
            }
            broker.put(-1);
            System.out.println("produced termination signal in producer");
        }catch (InterruptedException e){
            e.printStackTrace();
            return;
        }
    }
}
