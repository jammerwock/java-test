package main.java;


import java.util.Arrays;

public class Producer implements Runnable{
    private final Q q;

    private int init = 1;


    public Producer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true){
            if(q.isDone()){
                break;
            }
            while (!q.isFull()){
                System.out.println(Thread.currentThread().getName() + " produced " + init);
                q.put(init);
                init++;
            }

        }

        System.out.println(Thread.currentThread().getName() + " all done");
        System.out.println("In producer" + Arrays.toString(q.getVal().toArray()));
    }
}
