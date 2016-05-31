package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Game;

public class GameOverMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView scoreView = (TextView) findViewById(R.id.tvPunktzehl);
        assert scoreView != null;
        scoreView.setText(Game.getCounter());
        setTitle("Game over");
    }

}
