package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher extends Thread {
    private AtomicInteger timesToEat;
    private String name;
    private Table table;
    public Philosopher(String name,int timesToEat, Table table){
        this.timesToEat = new AtomicInteger(timesToEat);
        this.name=name;
        this.table=table;
    }

    @Override
    public void run() {
        try {
            Dinner();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Dinner() throws InterruptedException {
        while (timesToEat.decrementAndGet() >=0 ){
            table.seatsAvailable.acquire();
            Eat();
            table.seatsAvailable.release();
            Walk();
        }
       table.personsAwaited.countDown();
    }

    private void Walk() throws InterruptedException, IllegalMonitorStateException {
        System.out.println("Philosopher "+ name +  " is walking");
        sleep(500);
    }
    private void Eat() throws InterruptedException, IllegalMonitorStateException {
        System.out.println("Philosopher "+ name +  " is eating");
        sleep(500);
        System.out.println("Philosopher "+ name +  " gets up off the table");
    }

    public static Philosopher[] createNPhilosophers(int qty, Table table){
        Philosopher[] philosophers = new Philosopher[qty];
        for (int i = 0; i < qty ; i++) {
            philosophers[i] = new Philosopher("Iuda"+ i, 3, table);
        }
        return philosophers;
    }

}
