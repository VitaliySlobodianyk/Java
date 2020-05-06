package com.company;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArraySum {

    public static  double  SumArrayMultithreaded(int[]arr,int threadCount) throws InterruptedException {
     int result=0;
     int subArraySize= arr.length/threadCount;
     int i=0, currentIndex=0;

        ThreadSum threadSum;
        if(arr.length%threadCount!=0){
            int left= arr.length%threadCount;
            threadSum = new ThreadSum(arr,currentIndex,currentIndex+subArraySize+left);
            currentIndex+=subArraySize+left;
            i++;
            threadSum.start();
            threadSum.join();
        }
      for (; i< threadCount; i++){

          threadSum = new ThreadSum(arr, currentIndex, currentIndex + subArraySize);
          currentIndex += subArraySize;

         threadSum.start();
         threadSum.join();
         result+=threadSum.sum;
      }
     return result;
    }

    public static double SumArraySingleThread(@NotNull int[]arr){
        int  res=0;
        for (double i: arr) {
            res+= i;
        }
        return res;
    }

}

class ThreadSum extends Thread{
    public int sum=0;
    private int arrToSum[];
    private int startIndex;
    private int endIndex;

    public ThreadSum(int arrReference[],  int startIndex, int endIndex){
        super("Thread" + startIndex);
        this.arrToSum=arrReference;
        this.startIndex=startIndex;
        this.endIndex=endIndex;
    }
    @Override
    public void run() {
        for ( ; startIndex<endIndex; startIndex++ ){
            sum+=arrToSum[startIndex];
        }
    }
}


