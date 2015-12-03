package com.leruka.leruka.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.user.Login;

public class LoginActivity extends LerukaActivity {

    private EditText inputName;
    private EditText inputPass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get inputs
        this.inputName = (EditText) findViewById(R.id.inputUsername);
        this.inputPass = (EditText) findViewById(R.id.inputPassword);
        // init loading animation
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Einloggen");
        this.progressDialog.setMessage("Deine Eingaben werden überprüft...");
    }


    // Spinner
    public void showProgressDialog() {
        this.progressDialog.show();
    }

    public void hideProgressDialog() {
        this.progressDialog.dismiss();
    }

    public void onLogin(View view) {
        // show spinner
        this.showProgressDialog();
        // Get username and password
        String name = this.inputName.getText().toString();
        String pass = this.inputPass.getText().toString();
        // Trigger login in the backend
        Login.login(name, pass);
    }

    public void onReceiveLogin() {
        // Show a success message
        Message.showMessage("Login war erfolgreich");
        // Go to the main menu
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

}
