package com.leruka.leruka.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Game;
import com.leruka.leruka.main.Central;

public class GameOverMenuActivity extends LerukaActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView scoreView = (TextView) findViewById(R.id.tvScore);
        assert scoreView != null;
        scoreView.setText(Game.getCounter());
        setTitle("Game over");
    }

    public void onButtonRestart(View view) {
        this.restartGame();
    }

    public void onButtonMain(View view) {
        this.goToMainMenu();
    }

    @Override
    public void onBackPressed() {
        this.restartGame();
    }

    private void restartGame() {
        Intent intent = new Intent(this, GameMainActivity.class);
        startActivity(intent);
    }

    private void goToMainMenu() {
        Central.goToMain(this);
    }
}
