package com.docsconsole.voltile;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolatileClientApp {

    private static final Logger LOGGER = Logger.getLogger("Volatile");
    private static volatile int COUNTER = 0;

    public static void main(String[] args) {

        IncrementListner il = new IncrementListner();
        Incrementer  i = new Incrementer();
        Thread t1 =new Thread(il);
        t1.start();
        Thread t2 = new Thread(i);
        t2.start();
    }

    static class IncrementListner implements Runnable {
        @Override
        public void run() {
            int localCounter = COUNTER;
            while ( localCounter < 5){
                if( localCounter!= COUNTER){
                    LOGGER.log(Level.INFO,"Got Change for COUNTER : {0}", COUNTER);
                    localCounter= COUNTER;
                }
            }
        }
    }

    static class Incrementer implements Runnable{
        @Override
        public void run() {

            int localCounter = COUNTER;
            while (COUNTER <5){
                LOGGER.log(Level.INFO, "Incrementing COUNTER to {0}", localCounter+1);
                COUNTER = ++localCounter;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}

