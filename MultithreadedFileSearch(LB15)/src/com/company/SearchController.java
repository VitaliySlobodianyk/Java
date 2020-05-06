package com.company;
import java.io.*;
import java.util.*;

public class SearchController {

    private static long workersCount = 0;
    ArrayList<String> searchResult;
    File startDirectory;
    String pattern;

    public  synchronized  static long GetNumber(){
        return ++workersCount;
    }

    public SearchController(String startDirectory, String pattern) {
        searchResult = new ArrayList<String>();
        this.pattern = pattern;
        this.startDirectory = new File(startDirectory);

    }

    public void startSearch() throws Exception {
           Thread searchWorker =  new Thread( new SearchWorker( startDirectory,searchResult, pattern), " # initial Search Worker " + GetNumber());
            searchWorker.start();
            searchWorker.join();
        }

}

