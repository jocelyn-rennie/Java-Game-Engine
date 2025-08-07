package org.uob.a2.gameobjects;


public class Stopwatch
{
    private long startTime;
    private long stopTime;
    private boolean running;

    // Start the stopwatch
    public void start() 
    {
        startTime = System.nanoTime();
        running = true;
    }

    // Stop the stopwatch
    public void stop() 
    {
        if (running) 
        {
            stopTime = System.nanoTime();
            running = false;
        }
    }

    public long getElapsedTimeNano() 
    {
        return running ? System.nanoTime() - startTime : stopTime - startTime;
    }

    //gets elapsed time in seconds
    public double getElapsedTimeSeconds() 
    {
        return getElapsedTimeNano() / 1_000_000_000.0;
    }


    
}