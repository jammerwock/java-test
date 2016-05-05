package main.java.app;


public class Producer implements Runnable{

    private Warehouse w;
    private int counter;

    public Producer(Warehouse w) {
        this.w = w;
        counter = 0;
    }

    @Override
    public void run() {
        while(true){
            while (!w.isAllowedProducing()){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (w.isDone()){
                break;
            }
            w.fill();
            if(counter++ == 2){
                w.setDone();
            }
            w.notifyAll();
        }
    }
}
