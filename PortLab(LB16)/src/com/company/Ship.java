package com.company;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Ship  extends  Thread{
  private String name;
  public AtomicInteger boxesCount;
  public Port port;
  private Dock dockForUnload;



    public Ship( String name ,int boxesCount, Port port){
        this.name =name;
        this.boxesCount  = new AtomicInteger(boxesCount);
        this.port= port;
    }
    public String getShipName(){
        return name;
    }

    @Override
    public void run() {
        try {
            askForUnload();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void askForUnload() throws InterruptedException {

            while(dockForUnload==null){
              dockForUnload = port.giveFreeDock(this);
              currentThread().sleep(2000);
            }
            if(dockForUnload!=null){
                System.out.println( name + " has got dock " + dockForUnload.name);
                dockForUnload.Unload(this);
                System.out.println( name + " has completed unload in " + dockForUnload.name);
                port.allUnloaded.countDown();
            }
    }


    public static Ship[] createNShips(int quantity, Port port){
        //Random rand = new Random();
        Ship[] ships = new Ship[quantity];
        for (int i = 0; i < quantity ; i++) {
            ships[i] = new Ship("Ship"+ i,10, port ); // rand.nextInt(20)
        }
        return ships;
    }

}
