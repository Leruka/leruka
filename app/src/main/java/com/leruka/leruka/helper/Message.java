package com.leruka.leruka.helper;

import android.widget.Toast;

import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ErrorCodes;

/**
 * This Class provides Methods for displaying messages to the user
 */
public class Message {

    private static final int MESSAGE_DURATION = Toast.LENGTH_LONG;

    public static void showErrorMessage(final String message) {

        Central.getCurrentActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        Toast errorToast = Toast.makeText(Central.getCurrentActivity().getApplicationContext(), message, MESSAGE_DURATION);
                        errorToast.show();
                    }
                }
        );

    }

    public static void showMessage(final String message) {
        Central.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast messageToast = Toast.makeText(Central.getCurrentActivity().getApplicationContext(), message, MESSAGE_DURATION);
                messageToast.show();
            }
        });
    }

    public static void showErrorMessage(int errorCode) {
        if (errorCode == ErrorCodes.USER_NAME_USED)
            showErrorMessage("Der Benutzername wird schon verendet");
        else if (errorCode == ErrorCodes.USER_NAME_INVALID)
            showErrorMessage("Der Benutzername ist nicht vergeben");
        else if (errorCode == ErrorCodes.USER_PASS_INVALID)
            showErrorMessage("Das Paswort ist falsch");
        else if (errorCode == ErrorCodes.DB_UNKNOWN_ERROR)
            showErrorMessage("Es ist ein interner Fehler aufgetreten");
        else
            showErrorMessage("Ein unbekannter Feheler ist aufgetreten");
    }



}
