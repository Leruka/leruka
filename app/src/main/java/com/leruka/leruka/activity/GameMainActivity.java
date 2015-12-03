package com.leruka.leruka.activity;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Game;
import com.leruka.leruka.main.Central;

public class GameMainActivity extends LerukaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_main);

        // Set Resources
        Central.setResources(getResources());

        // Trigger start when surface is ready
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(Game.getSurfaceHandler());

    }

}
