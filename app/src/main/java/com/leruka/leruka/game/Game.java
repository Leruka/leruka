package com.leruka.leruka.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.leruka.leruka.game.process.DrawProcess;
import com.leruka.leruka.game.process.MainProcess;
import com.leruka.leruka.game.track.Track;
import com.leruka.leruka.game.track.stage.Stage1;
import com.leruka.leruka.game.track.stage.Stage2;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.input.Gesture;
import com.leruka.leruka.main.Central;

/**
 * This class is controlling all the actions that have to be done in order to run the game itself.
 * More complex functions should not be programmed in here. It's just kind of a controlling class.
 */
public class Game {

    /** The current track of the game */
    private static Track track;
    /** The player object of the game */
    private static Player player;
    /** The surfaceHolder to render the game on. It is used to get a canvas */
    private static SurfaceHolder surfaceHolder;
    /** The SurfaceHandler, which is triggered when the activity is ready to start */
    private static SurfaceHandler surfaceHandler;
    /** Counter for points*/
    private static int counter;

    /**
     * Creates the default surface handler
     */
    static {
        surfaceHandler = new SurfaceHandler();
    }

    /**
     * Sets everything this class has to to up ready for running.
     * It is not necessary to init each time you want to start a new track, just update the track.
     */
    private static void init() {
        // init main process
        MainProcess.init();
        // init draw process
        DrawProcess.init();
        // Create Player
        player = new Player(Central.getDisplayHeight() - Measure.ph(5));
        // Create Track
        track = createTrack(1);
    }

    /**
     * Starts the main and draw threads.
     */
    public static void start() {
        MainProcess.run();
        DrawProcess.run();
    }

    /**
     * Triggers to start the game. Should be used as a result from starting the game activity.
     * @param surfaceHolder The surfaceHolder on which the game will be rendered. Must not be null
     */
    private static void startFromView(SurfaceHolder surfaceHolder) {
        init();
        Game.surfaceHolder = surfaceHolder;
        start();
    }

    /**
     * Stops the main and draw thread. This will result in the game stopping from running,
     * nothing more.
     */
    public static void stop() {
        MainProcess.end();
        DrawProcess.end();
    }


    /**
     * This method will cause the game to be rendered one time to the surface holder.
     */
    public static void redraw() {
        Canvas canvas = surfaceHolder.lockCanvas();
        track.draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    /**
     * This method will cause the game to progress one tick.
     */
    public static void tick() {
        track.update();
        //TODO Hintergrund & Hinderniss bewegen
        //TODO Kollision prüfen
        //TODO Animationen aktualisieren
        //TODO Punktestand merken
        increaseCounter();
    }


    /**
     * This method will process a given gesture: Up > player jump, Down > player duck.
     * @param gesture The gesture to progress
     */
    public static void processGesture(Gesture.Type gesture) {
        switch (gesture) {
            case UP:
                player.jump();
                break;
            case DOWN:
                player.duck();
                break;
        }
    }

    /**
     * Use this method if you need to get a SurfaceHolder.Callback for triggering the game start.
     * @return the default surface handler
     */
    public static SurfaceHolder.Callback getSurfaceHandler() {
        return surfaceHandler;
    }

    private static Track createTrack(int number) {
        if (number == 1) {
            return new Stage1(player);
        }
        else {
            return new Stage2(player);
        }
    }

    public static String getCounter() {
        return Integer.toString(counter / 100);
    }

    public static void increaseCounter() {
        Game.counter++;
    }

    /**
     * This class is used to wait for the Surface (Activity) to be ready and start the game when so.
     */
    static class SurfaceHandler implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Game.startFromView(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {}
    }
}
