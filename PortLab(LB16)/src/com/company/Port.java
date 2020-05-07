package com.company;
import java.util.concurrent.*;
public class Port extends Thread {

    private Dock[] docks;
    public CountDownLatch allUnloaded;
    public Port (int docksQty, int plannedShipsToUnload){
        docks = createNDocks(docksQty);
        allUnloaded= new CountDownLatch(plannedShipsToUnload);
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
    @Override
    public void run(){
        try {
            allUnloaded.await();
            System.out.println("All planned Ships Unloaded\n Port closes for maintenance works! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
