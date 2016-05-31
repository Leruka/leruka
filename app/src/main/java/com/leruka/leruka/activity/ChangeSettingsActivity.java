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
import com.leruka.leruka.user.ChangeSettings;
import com.leruka.leruka.user.LUser;
import com.leruka.leruka.user.LoginResult;
import com.leruka.protobuf.ErrorCodes;
import com.leruka.protobuf.User;

public class ChangeSettingsActivity extends LerukaActivity {

    ProgressDialog progressDialog;

    private EditText inputName;
    private EditText inputPassCurrent;
    private EditText inputPass1;
    private EditText inputPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Einstellungen");

        this.inputName = (EditText) findViewById(R.id.inputNewName);
        this.inputPassCurrent = (EditText) findViewById(R.id.inputCurrentPassword);
        this.inputPass1 = (EditText) findViewById(R.id.inputNewPassword);
        this.inputPass2 = (EditText) findViewById(R.id.inputNewPasswordRepeat);

        // Fill in nick
        this.inputName.setText(LUser.getCurrentUser().getUserName());

        // init loading animation
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Änderungen übernehmen");
        this.progressDialog.setMessage("Deine Eingaben werden überprüft...");

        // Loose focus
        this.inputName.clearFocus();
    }

    private void changePassword(String newPass1, String newPass2, String oldPass) {

        LoginResult result = ChangeSettings.password(newPass1, newPass2, oldPass);

        // If request has bot been send, show error
        if (!result.isSuccess()) {
            Message.showErrorMessage(result.getMessage());
        }
        else {
            this.showProgressDialog();
        }
    }

    private void changeUsername(String newName, String oldPass) {
        LoginResult result = ChangeSettings.name(newName, oldPass);

        // If request has bot been send, show error
        if (!result.isSuccess()) {
            Message.showErrorMessage(result.getMessage());
        }
        else {
            this.showProgressDialog();
        }
    }

    private void changeBoth(String newName, String newPass1, String newPass2, String oldPass) {
        LoginResult result = ChangeSettings.both(newName, newPass1, newPass2, oldPass);

        // If request has not been send, show error
        if (!result.isSuccess()) {
            Message.showErrorMessage(result.getMessage());
        }
        else {
            this.showProgressDialog();
        }
    }

    public void onSave(View view) {

        // Gather data
        String pwOld = this.inputPassCurrent.getText().toString();
        String pw1 = this.inputPass1.getText().toString();
        String pw2 = this.inputPass2.getText().toString();
        String name = this.inputName.getText().toString();

        // find out what to change
        boolean changeName = !name.isEmpty() && !LUser.getCurrentUser().getUserName().equals(name);
        boolean changePass = !pw1.isEmpty() || !pw2.isEmpty();

        // Change Name
        if (changeName) {
            // Change Both
            if(changePass) {
                changeBoth(name, pw1, pw2, pwOld);
            } else {
                changeUsername(name, pwOld);
            }
        }
        // Change pass
        else if (changePass) {
            changePassword(pw1, pw2, pwOld);
        }
        // Nothing to change
        else {
            Message.showMessage("Keine Änderungen");
            this.goToMainActivity();
        }
    }

    public void onCancel(View view) {
        this.goToMainActivity();
    }

    public static void processResponse(User.ResponseChangeSettings changeSettings) {
        // Check for success
        if (changeSettings != null && changeSettings.getSuccess()) {
            ChangeSettingsActivity.receiveSuccessChangeSettings();
        }
        else {
            ChangeSettingsActivity.receiveFailedChangeSettings(changeSettings);
        }
    }

    private void goToMainActivity() {
        // Go to the main menu
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

    private void logout() {
        LUser.logout();
        // Go to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private static void receiveSuccessChangeSettings() {
        // Show a success message
        Message.showMessage("Änderungen erfolgreich");

        // If still in this activity, change it
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(ChangeSettingsActivity.class)) {
            ((ChangeSettingsActivity) currentActivity).logout();
        }
    }

    private static void receiveFailedChangeSettings(User.ResponseChangeSettings changeSettings) {
        // If still in this activity, hide the spinner
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(ChangeSettingsActivity.class)) {
            ((ChangeSettingsActivity) currentActivity).hideProgressDialog();
        }

        if (changeSettings == null) {
            Message.showErrorMessage("Es gab ein unbekanntes Problem bei der Kommunikation mit dem Server");
            return;
        }

        if (changeSettings.getErrorCodeCount() < 1) {
            Message.showErrorMessage("Änderungen fehlgeschlagen, bitte versuche es später erneut");
            return;
        }

        for (ErrorCodes.ErrorCode c : changeSettings.getErrorCodeList()) {
            Message.showErrorMessage(com.leruka.leruka.net.ErrorCodes.getReadableError(c));
        }
    }

    // Spinner
    public void showProgressDialog() {
        this.progressDialog.show();
    }

    public void hideProgressDialog() {
        this.progressDialog.dismiss();
    }


}
