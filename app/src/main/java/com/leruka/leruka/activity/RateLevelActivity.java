package com.leruka.leruka.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.protobuf.ErrorCodes;
import com.leruka.protobuf.Rating;

public class RateLevelActivity extends LerukaActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setTitle("Bewerten Sie das Level.");

    }

    public static void processResponse(Rating.ResponseRateLevel rateLevel) {
        // Check for success
        if (rateLevel != null && rateLevel.getSuccess()) {
            RateLevelActivity.receiveSuccessRateLevel(rateLevel);
        }
        else {
            RateLevelActivity.receiveFailedRateLevel(rateLevel);
        }
    }

    private static void receiveSuccessRateLevel(Rating.ResponseRateLevel rateLevel) {
        // Show a success message
        Message.showMessage("Du hast das Level erfolgreich bewertet!");

        // If still in this activity, change it
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(RateLevelActivity.class)) {
            RateLevelActivity activity = ((RateLevelActivity) currentActivity);
        }
    }

    private static void receiveFailedRateLevel(Rating.ResponseRateLevel rateLevel) {
        // If still in this activity, hide the spinner
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(RateLevelActivity.class)) {
            ((RateLevelActivity) currentActivity).hideProgressDialog();
        }

        // Check for null
        if (rateLevel == null) {
            Message.showErrorMessage("Es gab ein unbekanntes Problem bei der Kommunikation mit dem Server");
            return;
        }

        // Check, if an error code has been send
        if (rateLevel.getErrorCodeCount() < 1) {
            Message.showErrorMessage("Einstellungen ändern fehlgeschlagen, bitte versuche es später erneut.");
            return;
        }

        // Show all error codes
        for (ErrorCodes.ErrorCode code : rateLevel.getErrorCodeList()) {
            Message.showErrorMessage(com.leruka.leruka.net.ErrorCodes.getReadableError(code));
        }
    }

    public void hideProgressDialog() {
        this.progressDialog.dismiss();
    }

}
