package main.java;


public class Consumer implements Runnable{

    final Broker<Integer> broker;

    public Consumer(Broker<Integer> broker) {
        this.broker = broker;
    }

    @Override
    public void run() {

        try{
            for(Integer message = broker.take(); message != -1; message = broker.take()){
                System.out.format("consumed: %s%n", message);
                Thread.sleep(1000);
            }
            System.out.println("received termination signal in consumer");
        }catch (InterruptedException e){
            e.printStackTrace();
            return;
        }



    }
}
