package main.java;

public class Bee extends Thread{
    final HoneyPot pot;

    public Bee(HoneyPot pot){
        this.pot = pot;
    }

    @Override
    public void run(){
//        while(true){
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            synchronized (pot){
//                while (pot.isFull()){
//                    try {
//                        pot.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                pot.addPortion();
//                if(pot.isFull()){
//                    pot.notifyAll();
//                }
//            }
//
//        }

        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (pot){
                if(pot.islast()){
                    System.out.println("Bee's last pot");
                    break;
                }
                if(!pot.isFull()){
                    pot.addPortion();
                    if(pot.isFull()){
                        pot.notifyAll();
                    }
                }
            }
        }


    }
}
