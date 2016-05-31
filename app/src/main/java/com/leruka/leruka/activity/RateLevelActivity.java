package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.user.LoginResult;
import com.leruka.leruka.user.RateLevels;
import com.leruka.protobuf.ErrorCodes;
import com.leruka.protobuf.Rating;

public class RateLevelActivity extends LerukaActivity {

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        //setTitle("Bewerten Sie das Level.");

    }

    public void onRate(View view) {
        int rating = (int) (2 * this.ratingBar.getRating());
        LoginResult result = RateLevels.rateLevel(rating, 1);
        if (!result.isSuccess()) {
            Message.showErrorMessage(result.getMessage());
        }
    }

    public void onFetch(View view) {
        RateLevels.getRating(1);
    }

    public static void processResponse(Rating.ResponseRateLevel rateLevel) {
        // Check for success
        if (rateLevel != null && rateLevel.getSuccess()) {
            RateLevelActivity.receiveSuccessRateLevel();
        }
        else {
            RateLevelActivity.receiveFailedRateLevel(rateLevel);
        }
    }

    private static void receiveSuccessRateLevel() {
        // Show a success message
        Message.showMessage("Du hast das Level erfolgreich bewertet!");
    }

    private static void receiveFailedRateLevel(Rating.ResponseRateLevel rateLevel) {

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

}
