package com.company;

public class Main {

    public static void main(String[] args) throws Exception {

        SearchController search = new SearchController("D:\\", "liq.html");
        TimeMeter.MeasureTime(()->{
            search.startSearch();
        });


     if(!search.searchResult.isEmpty()){
         System.out.println("Found Files " + search.searchResult.size() + " : ");
         for (String s : search.searchResult)
             System.out.println(s);
     }else{
         System.out.println("There are no files that match current pattern");
     }

    }
}
