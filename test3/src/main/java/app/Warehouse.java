package main.java.app;


public class Warehouse {
    private final int[] storage;

    private static final int value = 5;

    private boolean allowedProducing;
    private boolean isDone;

    public Warehouse(int size) {
        storage = new int[size];
        allowedProducing = true;
        isDone = false;
    }

    public int getSize(){
        return storage.length;
    }

    synchronized public void fill(){
        storage[0] += value;
        allowedProducing = false;
        System.out.println(Thread.currentThread().getName() + " fill");
    }

    synchronized public void decrement(){
        if(storage[0] == 0){
            allowedProducing = true;
            System.out.println(Thread.currentThread().getName() + " allowed");
        }else{
            --storage[0];
            System.out.println(Thread.currentThread().getName() + " decrement");
        }


    }

    public boolean isAllowedProducing(){
        return allowedProducing;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

}
