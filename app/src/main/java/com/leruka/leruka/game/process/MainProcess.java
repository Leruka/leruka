package com.leruka.leruka.game.process;

import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;

/**
 * This class is responsible for updating everything logical.
 */
public class MainProcess {

    private static boolean isRunning;
    private static boolean isPaused;
    private static Thread thread;

    /**
     * Prepares the Process for running.
     */
    public static void init() {
        isRunning = false;
        isPaused = false;
        thread = new Thread(new MainProcessThread());
    }

    /**
     * Starts the process / Thread
     */
    public static void run() {
        // Check, if there is a thread and if it is not yet running
        if (thread == null || thread.isAlive()) {
            if (Central.isDev) Message.showErrorMessage("main Thread already running or undefined!");
            return;
        }
        thread.start();
    }

    /**
     * Contains everything that has to be done each tick
     */
    protected static void tick() {
        //TODO Hintergrund & Hinderniss bewegen
        //TODO Kollision prüfen
        //TODO Animationen aktualisieren
        //TODO Punktestand merken
    }

    /**
     * This class contains the main thread that triggers each tick
     */
    private static class MainProcessThread implements Runnable {

        /**
         * The main method that is running the Thread
         */
        @Override
        public void run() {
            // init time tracking & co
            long start;
            long targetNanoTime = Central.targetTickNanos;
            // Set up running
            isRunning = true;
            // wrap whole while loop, you do not want to create a try-catch every time
            try {
                while (isRunning) {
                    start = System.nanoTime();
                    if (!isPaused) MainProcess.tick();
                    Thread.sleep(
                            targetNanoTime - (System.nanoTime() - start)
                    );
                }
            } catch (InterruptedException e) {
                // This should not happen
                Message.showErrorMessage("Main Thread has been interrupted!");
                //TODO what to do? go to main menu? quit? WHAT? AND WHO DID IT???
            }


        }
    }

}
