package com.leruka.leruka.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.user.LUser;
import com.leruka.leruka.user.Login;
import com.leruka.leruka.user.LoginResult;
import com.leruka.protobuf.ErrorCodes;
import com.leruka.protobuf.User;

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

        // Get username and password
        String name = this.inputName.getText().toString();
        String pass = this.inputPass.getText().toString();

        // Trigger login in the backend
        LoginResult result = Login.login(name, pass);

        // When the request has not been send, show an error
        if (!result.isSuccess()) {
            Message.showErrorMessage(result.getMessage());
        }
        else {
            // show spinner, if send
            this.showProgressDialog();
        }
    }

    private void goToMainActivity() {
        // Go to the main menu
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

    // Static

    public static void processResponse(User.ResponseLogin login) {
        // Check for success
        if (login != null && login.getSuccess() && login.getSessionID() != null) {
            LoginActivity.receiveSuccessLogin(login);
        }
        else {
            LoginActivity.receiveFailedLogin(login);
        }
    }

    private static void receiveSuccessLogin(User.ResponseLogin login) {
        // Show a success message
        Message.showMessage("Login war erfolgreich");

        // Set the session ID
        LUser.setSessionID(login.getSessionID());

        // If still in this activity, change it
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(LoginActivity.class)) {
            ((LoginActivity) currentActivity).goToMainActivity();
        }
    }

    private static void receiveFailedLogin(User.ResponseLogin login) {
        // If still in this activity, hide the spinner
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(LoginActivity.class)) {
            ((LoginActivity) currentActivity).hideProgressDialog();
        }

        // Check for null
        if (login == null) {
            Message.showErrorMessage("Es gab ein unbekanntes Problem bei der Kommunikation mit dem Server");
            return;
        }

        // Check, if an error code has been send
        if (login.getErrorCodeCount() < 1) {
            Message.showErrorMessage("Login fehlgeschlagen, bitte versuche es später erneut.");
            return;
        }

        // Show all error codes
        for (ErrorCodes.ErrorCode code : login.getErrorCodeList()) {
            Message.showErrorMessage(com.leruka.leruka.net.ErrorCodes.getReadableError(code));
        }

    }


}
