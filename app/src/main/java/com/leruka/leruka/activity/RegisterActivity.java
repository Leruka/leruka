package com.leruka.leruka.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.leruka.leruka.R;
import com.leruka.leruka.user.Register;

public class RegisterActivity extends LerukaActivity {

    private EditText inputName;
    private EditText inputPass1;
    private EditText inputPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.inputName = (EditText) findViewById(R.id.inputUsername);
        this.inputPass1 = (EditText) findViewById(R.id.inputPassword1);
        this.inputPass2 = (EditText) findViewById(R.id.inputPassword2);
    }

    public void onRegister(View view) {

        String name = this.inputName.getText().toString();
        String pass1 = this.inputPass1.getText().toString();
        String pass2 = this.inputPass2.getText().toString();

        Register.register(name, pass1, pass2);

    }

    public void onReceiveRegister() {
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

    private void onBack() {
        //TODO
    }

}
