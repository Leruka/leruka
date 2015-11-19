package com.leruka.leruka.helper;

import android.widget.Toast;

import com.leruka.leruka.main.Central;

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



}
