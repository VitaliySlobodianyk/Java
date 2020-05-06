package com.company;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class SearchWorker implements Runnable {

        String pattern;
        ArrayList<File> queue;
        ArrayList<String> searchResult;

        public boolean isRoot(File f) {
            File roots[] = File.listRoots();
            for (File i : roots) {
                if (f.getAbsolutePath().equals(i.getAbsolutePath()))
                    return true;
            }
            return false;
        }

        public SearchWorker( File startDirectory, ArrayList<String> searchResult, String pattern) {
            queue= new ArrayList<File>();

            this.searchResult = searchResult;
            this.pattern = pattern;
            System.out.println("Initialized search in directory: " +Thread.currentThread().getName());
            try{
               queue.addAll(Arrays.asList(startDirectory.listFiles()));
            } catch (NullPointerException ex){
             //  System.out.println("Folder is empty:"+ startDirectory);
            }


        }

        @Override
        public void run() {
           try{
               for( File file : queue){
                   if (file.getAbsolutePath().toLowerCase().contains(pattern.toLowerCase())){
                       searchResult.add(file.getAbsolutePath());
                   }

                   if (file.isDirectory() || isRoot(file)) {
                       Thread searchWorker =  new Thread( new SearchWorker( file,searchResult, pattern), "Search Worker #" + SearchController.GetNumber());
                       searchWorker.start();
                       searchWorker.join();
                   }
               }
                } catch (InterruptedException ex) {

                }


        }
}
