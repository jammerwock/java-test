package main.java;


public class Main {
    public static void main(String[] args) {
            Q q = new Q(6);
            Producer p = new Producer(q);
            Consumer c = new Consumer(q);


        int numTreads = 1;
        runThreads(c, "Consumer", numTreads);
        runThreads(p, "Producer", numTreads);


//        System.out.println("main finish");
    }

    public static void runThreads(Runnable r, String name, int num){
        for (int i = 0; i < num; ++i){
            Thread t = new Thread(r, name + i);
            t.start();
            System.out.println(t.getName() + " started");
//            try {
//                t.join();
//                System.out.println(t.getName() + " joined");
//            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
