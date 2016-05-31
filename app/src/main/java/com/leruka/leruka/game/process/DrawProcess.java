package com.leruka.leruka.game.process;

import android.util.Log;

import com.leruka.leruka.game.Game;

/**
 * Created by leif on 03.12.15.
 */
public class DrawProcess {

    private static boolean isRunning;
    private static final int sleepTime = 50;

    private static DrawProcessThread threadClass;
    private static Thread thread;

    public static void init() {
        isRunning = false;
        // Quit running thread, if there is one
        if (threadClass != null) threadClass.updateIsRunning();
        // Create new Thread
        threadClass = new DrawProcessThread();
        thread = new Thread(threadClass);
    }

    public static void run() {
        isRunning = true;
        thread.start();
    }

    private static void redraw() {
        Game.redraw();
    }

    public static void end() {
        isRunning = false;
        threadClass.updateIsRunning();
    }

    static class DrawProcessThread implements Runnable {

        private boolean isRunning;

        DrawProcessThread() {
            super();
            this.isRunning = false;
        }

        @Override
        public void run() {
            this.isRunning = true;
            try {
                while (this.isRunning) {
                    DrawProcess.redraw();
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Log.i("leruka", "draw thread has been interupted");
                Thread.currentThread().interrupt();
            }
        }

        void updateIsRunning() {
            this.isRunning = DrawProcess.isRunning;
        }
    }

}
