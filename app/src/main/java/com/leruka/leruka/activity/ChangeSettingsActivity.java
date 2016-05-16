package com.leruka.leruka.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.user.ChangeSettings;
import com.leruka.leruka.user.LUser;
import com.leruka.protobuf.ErrorCodes;
import com.leruka.protobuf.User;

public class ChangeSettingsActivity extends LerukaActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Einstellungen");
    }

    public void onChangePassword(View view) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void onChangeUsername(View view) {
        Intent intent = new Intent(this, ChangeDisplaynameActivity.class);
        startActivity(intent);
    }

    public static void processResponse(User.ResponseChangeSettings changeSettings) {
        // Check for success
        if (changeSettings != null && changeSettings.getSuccess()) {
            ChangeSettingsActivity.receiveSuccessChangeSettings(changeSettings);
        }
        else {
            ChangeSettingsActivity.receiveFailedChangeSettings(changeSettings);
        }
    }

    private static void receiveSuccessChangeSettings(User.ResponseChangeSettings changeSettings) {
        // Show a success message
        Message.showMessage("Einstellungen wurden erfolgreich verändert");

        // If still in this activity, change it
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(ChangeSettingsActivity.class)) {
            ChangeSettingsActivity activity = ((ChangeSettingsActivity) currentActivity);
        }
    }

    private static void receiveFailedChangeSettings(User.ResponseChangeSettings changeSettings) {
        // If still in this activity, hide the spinner
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(ChangeSettingsActivity.class)) {
            ((ChangeSettingsActivity) currentActivity).hideProgressDialog();
        }

        // Check for null
        if (changeSettings == null) {
            Message.showErrorMessage("Es gab ein unbekanntes Problem bei der Kommunikation mit dem Server");
            return;
        }

        // Check, if an error code has been send
        if (changeSettings.getErrorCodeCount() < 1) {
            Message.showErrorMessage("Einstellungen ändern fehlgeschlagen, bitte versuche es später erneut.");
            return;
        }

        // Show all error codes
        for (ErrorCodes.ErrorCode code : changeSettings.getErrorCodeList()) {
            Message.showErrorMessage(com.leruka.leruka.net.ErrorCodes.getReadableError(code));
        }
    }

    public void hideProgressDialog() {
        this.progressDialog.dismiss();
    }


}
