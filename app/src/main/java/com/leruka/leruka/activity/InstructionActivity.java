package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.leruka.leruka.R;

public class InstructionActivity extends LerukaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void onBack() {
        //TODO
    }

}
