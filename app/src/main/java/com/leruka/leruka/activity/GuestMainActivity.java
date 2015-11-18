package com.leruka.leruka.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.leruka.leruka.R;
import com.leruka.leruka.user.Login;

public class GuestMainActivity extends LerukaActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
