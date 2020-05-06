package com.company;

class Factorial implements Runnable{


    private  long  GetFactorial( long number){
        long res= 1;
        for (int i=2; i<=number; i++){
            res*=i;
        }
        return res;
    }
    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        System.out.println( "Thread name:" + Thread.currentThread().getName() + " Factorial:"  +  GetFactorial(Long.parseLong(Thread.currentThread().getName())));
    }
}
class FactorialExt extends  Thread{
   FactorialExt(String name){
       super(name);
   }
    private  long  GetFactorial( long number){
        long res= 1;
        for (int i=2; i<=number; i++){
            res*=i;
        }
        return res;
    }
    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        System.out.println( "Thread name:" + Thread.currentThread().getName() + " Factorial:"  +  GetFactorial(Long.parseLong(Thread.currentThread().getName())));
    }
}

class Counter extends Thread{
    int numToCount;
    Counter(String name, int num){
         super(name);
         this.numToCount=num;
     }

    public void run() {
      for(int i=0; i<numToCount;i++){
          System.out.println( "Thread name:" + Thread.currentThread().getName() + " Counts: " + i);
        try{
          Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      }
    }
}

public class Main {

    public static void main(String[] args)  {
	 for(int i=0; i<10; i++){
	     Thread t = new Thread(new Factorial(), ""+i);

	     t.start();
     }

	try{
	 Thread.currentThread().sleep(1000);
	}catch (InterruptedException e) {
        e.printStackTrace();
    }
        System.out.println("Waiting");
        for(int i=0; i<10; i++){
            Thread t = new FactorialExt( ""+i);
            t.start();
        }

        try{
            Thread.currentThread().sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            Thread count = new Counter( String.valueOf(i), 10 );
            count.start();
        }

    }
}
