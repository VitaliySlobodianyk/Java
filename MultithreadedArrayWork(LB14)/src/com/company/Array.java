package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Array {

public static String PrintArray(@NotNull int[] array){
 String result = "";
    if(array.length>0){
        for (int i = 0; i < array.length-1 ; i++) {
            result+=  array[i]+", ";
        }
        result+=  array[array.length-1]+".";
    }else{
        result= "Array is empty";
    }
     return result;
}
public static void RandomizeArray(@NotNull int[]array){
     Random rand= new Random();
     for (int i=0; i< array.length;i++) {
          array[i] =rand.nextInt(100);
     }
}

 public static void Sort(int[] array) throws InterruptedException {

    for (int gap = array.length/2; gap > 0; gap /= 2)
     {
        Thread sortWorker= new Thread( new SortWorker(array,gap) ,"Worker Gap" + gap);
        sortWorker.start();
        sortWorker.join();
     }
 }

}
class SortWorker implements Runnable{
    private  int[] array;
    private  int gap;


    public SortWorker(int[] array, int gap){
        this.array=array;
        this.gap=gap;
    }
    @Override
    public void run() {
        sort();
    }

    public void sort(){
        for (int i = gap; i < array.length; i += 1)
        {
            int temp = array[i];
            int j;

            for (j = i; j >= gap && array[j - gap] > temp; j -= gap)
                array[j] = array[j - gap];

            array[j] = temp;
        }
    }
}
