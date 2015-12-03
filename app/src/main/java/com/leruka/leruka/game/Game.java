package com.leruka.leruka.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.leruka.leruka.game.process.DrawProcess;
import com.leruka.leruka.game.process.MainProcess;
import com.leruka.leruka.game.track.Track;
import com.leruka.leruka.game.track.creator.TestStage;

/**
 * This class is controlling all the actions that have to be done in order to run the game itself
 */
public class Game {

    private static Track track;
    private static SurfaceHolder surfaceHolder;
    private static SurfaceHandler surfaceHandler;

    static {
        surfaceHandler = new SurfaceHandler();
    }

    public static void init() {

        // init main process
        MainProcess.init();
        // init draw process
        DrawProcess.init();

        // Create Player
        Player player = new Player();
        // Create Track
        track = TestStage.createTrack(player);


    }

    public static void start() {
        MainProcess.run();
        DrawProcess.run();
    }

    public static void startFromView(SurfaceHolder surfaceHolder) {
        init();
        Game.surfaceHolder = surfaceHolder;
        start();
    }

    public static void redraw() {
        Canvas canvas = surfaceHolder.lockCanvas();
        track.draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public static void tick() {
        track.update();
        //TODO Hintergrund & Hinderniss bewegen
        //TODO Kollision prüfen
        //TODO Animationen aktualisieren
        //TODO Punktestand merken
    }

    public static SurfaceHolder.Callback getSurfaceHandler() {
        return surfaceHandler;
    }

    static class SurfaceHandler implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Game.startFromView(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
