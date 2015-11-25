package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.leruka.leruka.R;

/**
 * Created by Kassandra
 *
 * 20.11.2015
 */

public class GameInstructionActivity extends LerukaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_instruction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Spieleanleitung");
    }
}
