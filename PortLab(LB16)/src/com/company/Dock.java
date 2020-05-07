package com.company;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import  java.util.concurrent.locks.ReentrantLock;
public class Dock extends Thread {
  private ReentrantLock lock = new ReentrantLock();
  public String name;

  public Dock(String name){
       this.name=name;
  }

  public void  Unload(Ship ship){
        if(lock.isLocked()){
         try {
            int unloadedBoxes=0;
             while(ship.boxesCount.decrementAndGet() >= 0){
                 unloadedBoxes++;
                 System.out.println(ship.getShipName() + " unloaded box:" +  unloadedBoxes);
                 currentThread().sleep(500);
             }
         } catch (InterruptedException e) {
             e.printStackTrace();

         } finally {
             lock.unlock();
         }
        }
    }
    public synchronized boolean bookDock(){
     return lock.tryLock();
    }
}