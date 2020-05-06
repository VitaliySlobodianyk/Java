package com.company;

import javax.print.Doc;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Port port = new Port(3);
        Ship [] ships = Ship.createNShips(6, port);
        for (Ship ship: ships) {
            ship.start();
        }

        int allUnloaded=0;
        while(allUnloaded < ships.length){
            for (int i = 0; i < ships.length; i++) {
                if(ships[i].boxesCount.get() == 0){
                   allUnloaded++;
                }
            }
            Thread.currentThread().sleep(500);
        }

        System.out.println("Job has been completed");


    }
}
