package minesweeper;

import java.util.Observable;
import java.util.ArrayList;


public class Timer extends Observable {
    private long startTime;
    private double elapsedTime;
    private boolean isTiming;
    
    public void start() {
        if (isTiming) return;
        // prevent the creation of multiple threads doing the time counting.
        startTime = System.currentTimeMillis();
        isTiming = true;
        (new Thread(new Runnable() { 
            // need to run the time counting on another thread otherwise 
            // the while loop will run indefinitely as one thread cannot 
            // excute the mouseClicked() for the stopTime() and while loop.
            // at the same time. 
            @Override
            public void run() {
                while (isTiming) {
                    elapsedTime = (double) ((System.currentTimeMillis() - startTime) / 1000);
                    System.out.println(elapsedTime);
                    updateObservers();
                    try {
                        Thread.sleep(1000); 
                    } catch(InterruptedException e) {
                       e.printStackTrace(); 
                    }
                }
            }
        })).start();
    }
    public void stop() {
        isTiming = false;
    }
    public void reset() {
        isTiming = false;
        elapsedTime = 0;
        this.deleteObservers();
    }
    private void updateObservers() {
        setChanged();
        notifyObservers(elapsedTime);
    }
    public static void main(String[] args)
    {

    }
 
        
}
