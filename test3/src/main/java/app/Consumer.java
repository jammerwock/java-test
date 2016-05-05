package main.java.app;


public class Consumer implements Runnable {

    private Warehouse w;

    public Consumer(Warehouse w) {
        this.w = w;
    }

    @Override
    public void run() {

        while(true){
            while (w.isAllowedProducing()){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(w.isDone()){
                break;
            }
            w.decrement();
            w.notifyAll();
        }



    }
}
