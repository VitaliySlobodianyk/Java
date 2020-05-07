package com.company;

public class Main {

    public static void main(String[] args) {
	int seatsQty =2;
	int philosophersQty=5;
	Table table = new Table(seatsQty,philosophersQty);
	Philosopher[] philosophers = Philosopher.createNPhilosophers(philosophersQty, table);
	table.start();
        for (Philosopher philosopher: philosophers) {
            philosopher.start();
        }

    }
}
