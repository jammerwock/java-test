package main.java;


public class HoneyPot {
    private int potSize;
    private int portion;
    private int potCounter;
    private static int potLimit = 3;


    public HoneyPot(int potSize){
        this.potSize = potSize;
        potCounter = 0;
    }

    public void emptyPot(){
        portion = 0;
        ++potCounter;
        System.out.println("Empty pot");
    }

    public void addPortion(){
        ++portion;
        System.out.println("Add portion: "+ portion);
    }

    public boolean isFull(){
        return potSize == portion;
    }

    public boolean islast(){
        return potLimit == potCounter;
    }
}
