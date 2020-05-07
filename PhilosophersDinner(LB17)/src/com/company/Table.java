package com.company;
import java.util.concurrent.*;
public class Table  extends Thread{
    public Semaphore seatsAvailable;
    public CountDownLatch personsAwaited;
    public Table(int sitsQty, int personsQty){
        seatsAvailable= new Semaphore(sitsQty);
        personsAwaited = new CountDownLatch(personsQty);
    }
    @Override
    public void run() {
        try {
            OrganizeDinner();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void OrganizeDinner() throws InterruptedException {
        System.out.println("Dinner has been started! All guests are Welcome!");
        personsAwaited.await();
        System.out.println("All persons have eaten, waiters clean table!");
    }
}
