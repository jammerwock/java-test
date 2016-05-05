package main.java;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Q {

    private ArrayList<Integer> val;
    private final int maxSize;

    private boolean isReadable = true;

    AtomicInteger fillEmptyCycles = new AtomicInteger(6);

    public Q(final int maxSize) {
        this.maxSize = maxSize;
        val = new ArrayList<Integer>(maxSize);
    }

    synchronized public int get(){
        while(!isReadable()){
            try {
                System.out.println(Thread.currentThread().getName() + " waiting in get()");
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        int index = val.size() - 1;
        Integer tmp = val.get(index);
        val.remove(index);

        if(this.isEmpty()){

            fillEmptyCycles.getAndDecrement();
            System.out.println("Notify: empty");
            allowRead();
            notifyAll();

        }


        return tmp.intValue();
    }

    synchronized public void put(int i){
        while (isReadable()){
            try {
                System.out.println(Thread.currentThread().getName() + " waiting in put()");
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        val.add(i);
        if(this.isFull()){
            fillEmptyCycles.getAndDecrement();
            System.out.println("Notify: full");
            blockRead();
            notifyAll();
        }

    }

    synchronized public boolean isFull(){
        return  this.val.size() == this.maxSize;
    }

    synchronized public boolean isEmpty(){
        return  this.val.isEmpty();
    }

    synchronized public boolean isDone(){
//        System.out.println("For " + Thread.currentThread().getName() + " left: " + fillEmptyCycles);
        return fillEmptyCycles.intValue() <= 0;
    }

    synchronized public List<Integer> getVal(){
        return Collections.unmodifiableList(this.val);
    }



    synchronized public void allowRead(){
        this.isReadable = true;
    }

    synchronized public void blockRead(){
        this.isReadable = false;
    }

    synchronized public boolean isReadable(){
        return this.isReadable;
    }

}
