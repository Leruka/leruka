package com.leruka.leruka.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.leruka.leruka.R;
import com.leruka.leruka.user.Login;
import com.leruka.leruka.user.User;

public class LoginActivity extends LerukaActivity {

    private EditText inputName;
    private EditText inputPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get inputs
        this.inputName = (EditText) findViewById(R.id.inputUsername);
        this.inputPass = (EditText) findViewById(R.id.inputPassword);
    }

    public void onLogin(View view) {
        //TODO
        String name = this.inputName.getText().toString();
        String pass = this.inputPass.getText().toString();

        Login.login(name, pass);
    }

    public void onReceiveLogin() {
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

}
