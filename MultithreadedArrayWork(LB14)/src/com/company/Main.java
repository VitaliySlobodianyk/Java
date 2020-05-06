package com.company;

import java.util.Random;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int size= 10000000;
        int[] arr1 = new int[size];
        Array.RandomizeArray(arr1);


        System.out.println("\n\tClassic Multithreaded Sum: ");
        final double[] res1 = new double[3];
        TimeMeter.MeasureTime(()-> {  res1[0] = ArraySum.SumArrayMultithreaded(arr1,10);
        System.out.println("Result is: "+ res1[0]);
        }
        );
        System.out.println("\n\tSingle threaded Sum: ");
        TimeMeter.MeasureTime(()-> {res1[1]= ArraySum.SumArraySingleThread(arr1);
        System.out.println("Result is: "+ res1[1]);
        });

        System.out.println("\n\tMultithreaded Sum without \"join\":");
        TimeMeter.MeasureTime(()-> {
           MultithreadingWithoutJoin arrTr= new MultithreadingWithoutJoin(arr1);
            res1[2]= arrTr.Summarize(10);
            System.out.println("Result is: "+ res1[2]);
                }
        );

        System.out.println("\n\tShell Sort:");
        int[] arr2 = new int[100];
        Array.RandomizeArray(arr2);
        System.out.println("Unsorted Array:");
        System.out.println( Array.PrintArray(arr2));
        TimeMeter.MeasureTime(()->{
           Array.Sort(arr2);
        });
        System.out.println("Sorted Array:");
        System.out.println(Array.PrintArray(arr2));
    }
}
