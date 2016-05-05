package main.java;

public class Main {

    static final int PORTIONS = 5;
    static final int BEES = 3;

    public static void main(String[] args) throws InterruptedException {

        HoneyPot pot = new HoneyPot(PORTIONS);
        Bear bear = new Bear(pot);
        bear.start();

        for (int i = 0; i < BEES; i++) {
            Bee bee = new Bee(pot);
            bee.start();
            bee.join();
        }
        bear.join();
        System.out.println("Main finished");
    }
}
