package main.java;


import java.util.Arrays;

public class Consumer implements Runnable{
    private final Q q;



    public Consumer(Q q){
        this.q = q;
    }

    @Override
    public void run() {

        while(true){

            if(q.isDone()){
                break;
            }
            while (!q.isEmpty()){
                System.out.println(Thread.currentThread().getName() + " consumed " + q.get());
            }

        }

        System.out.println(Thread.currentThread().getName() + " all done");
        System.out.println("In consumer" + Arrays.toString(q.getVal().toArray()));

    }
}
