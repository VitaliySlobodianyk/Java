package com.company;

public class Port extends Thread {

    private Dock[] docks;

    public Port (int docksQuant){
        docks = createNDocks(docksQuant);
    }

    public synchronized Dock giveFreeDock(Ship ship) throws InterruptedException {
        for (Dock dock: docks){
           if(dock.bookDock()){
               return dock;
           }
        }
        return null;
    }

    public static Dock[] createNDocks(int quantity){
        Dock[] docks = new Dock[quantity];
        for (int i = 0; i < quantity ; i++) {
            docks[i] = new Dock("Dock "+ i);
        }
        return docks;
    }
}
