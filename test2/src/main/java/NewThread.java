package main.java;

public class NewThread implements Runnable {

    Thread t;
    public NewThread(){
        t = new Thread(this, "dddd");
        t.start();
    }

    @Override
    public void run() {
        System.out.println(t.getName());
    }
}
//public class NewThread extends Thread {
//
//    public NewThread(){
//        super("ss");
//    }
//
//    @Override
//    public void run() {
//        super.run();
//        System.out.println("NewThread");
//        System.out.println(getName());
//    }
//}
