package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MultithreadingWithoutJoin {
    private int[] array;
    private Semaphore allDone;

    public MultithreadingWithoutJoin( int[] array) {
        this.array= array;
    }

    private class Worker extends Thread {
        int start;
        int end;
        double sum;

        Worker(int start, int end) {
            this.start = start;
            this.end = end;
            sum = 0;
        }

        public void run() {
            for (int i=start; i<end; i++) {
                sum += array[i];
            }
            allDone.release();
        }

        public double getSum() {
            return sum;
        }
    }

    public double Summarize(int numWorkers) {
        allDone = new Semaphore(0);

        List<Worker> workers = new ArrayList<Worker>();
        int lenOneWorker = array.length / numWorkers;
        for (int i=0; i<numWorkers; i++) {
            int start = i * lenOneWorker;
            int end = (i+1) * lenOneWorker;

            if (i==numWorkers-1) end = array.length;
            Worker worker = new Worker(start, end);
            workers.add(worker);
            worker.start();
        }

        // Wait to finish (this strategy is an alternative to join())
        try {
            allDone.acquire(numWorkers);
        } catch (InterruptedException ignored) {
        }

        // Gather sums from workers (yay foreach!)
        int sum = 0;
        for (Worker w: workers) sum += w.getSum();

        return sum;
    }

}