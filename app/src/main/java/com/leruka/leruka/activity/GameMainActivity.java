package com.leruka.leruka.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Game;
import com.leruka.leruka.input.Gesture;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.sound.Music;

public class GameMainActivity extends LerukaActivity {

    private GestureDetector gestureDetector;
    private MediaPlayer backgroundmusic;
    private Music music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_main);

        // Set Resources: Are needed later on to load the bitmaps
        Central.setResources(getResources());

        // Trigger game start when surface is ready
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(Game.getSurfaceHandler());

        // Add the gesture detector
        this.gestureDetector = new GestureDetector(this, new GestureHandler());

        //Add background music
        // Add background music
        backgroundmusic = MediaPlayer.create(GameMainActivity.this, R.raw.music);
        music = new Music(backgroundmusic);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            goToFullscreen();
        }
    }

    @Override
    public void onBackPressed() {
        //TODO show quit question
        Game.stop();
        Music.pause();
        // Go to main menu
        //TODO make gotToMain for logged / not logged in
        Intent intent = new Intent(this, GuestMainActivity.class);
        startActivity(intent);
    }

    private void goToFullscreen() {
        int opts;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            opts = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        else {
            opts = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        getWindow().getDecorView().setSystemUiVisibility(opts);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    static class GestureHandler implements  GestureDetector.OnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Gesture.processGesture(e1, e2);
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }
    }

}
