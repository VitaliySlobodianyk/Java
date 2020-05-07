package com.company;

import javax.print.Doc;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int shipsQty =6;
        int docksQty=3;
        Port port = new Port( docksQty, shipsQty );
        port.start();
        Ship [] ships = Ship.createNShips(shipsQty, port);
        for (Ship ship: ships) {
            ship.start();
        }
    }
}
