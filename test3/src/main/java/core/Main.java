package main.java.core;


import main.java.app.Consumer;
import main.java.app.Producer;
import main.java.app.Warehouse;

public class Main {

    public static void main(String[] args) {

        final int size = 6;

        Warehouse w = new Warehouse(size);
        Producer p = new Producer(w);
        Thread tp = new Thread(p, "Producer thread");
        tp.start();

        try {
            tp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            Consumer c = new Consumer(w);
            Thread tc = new Thread(c, "Consumer " + i);
            tc.start();
            try {
                tc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
