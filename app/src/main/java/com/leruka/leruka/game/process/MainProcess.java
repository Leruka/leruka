package com.leruka.leruka.game.process;

import com.leruka.leruka.game.Game;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;

/**
 * This class is responsible for updating everything logical.
 */
public class MainProcess {

    private static boolean isRunning;
    private static boolean isPaused;
    private static Thread thread;
    private static MainProcessThread threadClass;

    /**
     * Prepares the Process for running.
     */
    public static void init() {
        isRunning = false;
        isPaused = false;
        // Quit running thread, if there is one
        if (threadClass != null) threadClass.updateIsRunning();
        // Create new Thread
        threadClass = new MainProcessThread();
        thread = new Thread(threadClass);
    }

    /**
     * Starts the process / Thread
     */
    public static void run() {
        // Check, if there is a thread and if it is not yet running
        if (thread == null || thread.isAlive()) {
            if (Central.isDev)
                Message.showErrorMessage("main Thread already running or undefined! Please init");
            return;
        }
        // Start thread
        isRunning = true;
        thread.start();
    }

    public static void end() {
        isRunning = false;
        threadClass.updateIsRunning();
    }

    /**
     * Contains everything that has to be done each tick
     */
    protected static void tick() {
        Game.tick();
    }

    /**
     * This class contains the main thread that triggers each tick
     */
    private static class MainProcessThread implements Runnable {

        private boolean isRunning;

        MainProcessThread() {
            super();
            this.isRunning = false;
        }

        /**
         * The main method that is running the Thread
         */
        @Override
        public void run() {
            System.out.println("MAIN");
            // init time tracking & co
            long start;
            long targetNanoTime = Central.targetTickNanos;
            // Set up running
            this.isRunning = true;
            // wrap whole while loop, you do not want to create a try-catch every time
            try {
                while (this.isRunning) {
                    start = System.nanoTime();
                    if (!isPaused) MainProcess.tick();
                    long sleep = (targetNanoTime - (System.nanoTime() - start)) / 1000000;
                    if (sleep > 0)
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                // This should not happen
                Message.showErrorMessage("Main Thread has been interrupted!");
                //TODO what to do? go to main menu? quit? WHAT? AND WHO DID IT???
            }
        }

        protected void updateIsRunning() {
            this.isRunning = MainProcess.isRunning;
        }
    }

}
