package main.java;


public class Bear extends Thread{
    final HoneyPot pot;

    public Bear(HoneyPot pot){
        this.pot = pot;
    }

    @Override
    public void run(){
       while(true){
           synchronized (pot){
               if(pot.islast()){
                   System.out.println("Bear last pot");
                   break;
               }
               while (!pot.isFull()){
                   try {
                       pot.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println("Bear awaken");
               pot.emptyPot();
//               pot.notifyAll();
           }

       }
    }
}
