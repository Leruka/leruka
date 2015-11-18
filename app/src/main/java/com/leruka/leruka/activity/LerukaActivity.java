package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.leruka.leruka.main.Central;

/**
 * Created by leif on 11.11.15.
 */
public abstract class LerukaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Central.setCurrentActivity(this);
    }

}
