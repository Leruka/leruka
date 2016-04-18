package com.leruka.leruka.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.leruka.leruka.R;
import com.leruka.leruka.user.User;

public class UserMainActivity extends LerukaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((TextView) findViewById(R.id.textWelcome))
                .setText("Hi, " + User.getCurrentUser().getUserName());

    }

    public void onLogout(View view) {
        Intent intent = new Intent(this, GuestMainActivity.class);
        startActivity(intent);
    }

    public void onAnleitung(View view) {
        Intent intent = new Intent(this, GameInstructionActivity.class);
        startActivity(intent);
    }

    public void onSpielStarten(View view) {
        Intent intent = new Intent(this, GameMainActivity.class);
        startActivity(intent);
    }

    public void onPrivateScore(View view) {
        Intent intent = new Intent(this, PrivateHighscoreActivity.class);
        startActivity(intent);
    }

    public void onPublicScore(View view) {
        Intent intent = new Intent(this, PublicHighscoreActivity.class);
        startActivity(intent);
    }

    public void onChangeSettings(View view) {
        Intent intent = new Intent(this, ChangeSettingsActivity.class);
        startActivity(intent);
    }
}
