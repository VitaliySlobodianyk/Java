package com.company;

    interface SupportedGeneralMethod{
        void SomeMethod() throws Exception;
    }

    public class TimeMeter {


        public static void MeasureTime(SupportedGeneralMethod func){
            long start= System.currentTimeMillis();
            try {
                func.SomeMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("Time of execution: " + (end-start)+ " ms");
        }
    }


