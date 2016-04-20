package com.leruka.leruka.user;

import android.app.Activity;
import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;
import com.leruka.leruka.activity.GuestMainActivity;
import com.leruka.leruka.activity.LoginActivity;
import com.leruka.leruka.activity.RegisterActivity;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.protobuf.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by leif on 09.11.15.
 */
public class Login {

    // Attributes
    private static final String LOGIN_URL = "http://78.46.212.166:8080/leruka/login"; //TODO get from res

    public static void login(String userName, String userPass) {
        // update User
        com.leruka.leruka.user.User user = new com.leruka.leruka.user.User(userName,  com.leruka.leruka.user.User.sha256(userPass));
        com.leruka.leruka.user.User.setCurrentUser(user);

        // validate
        if (!com.leruka.leruka.user.User.isValid()) {

        }


        try {
            sendLogin(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods
    protected static void sendLogin(com.leruka.leruka.user.User user) throws IOException {
        PostObject postObject = new PostObject(
                new URL(LOGIN_URL),
                ContentType.protobuf,
                getLoginBytes(
                        user.getUserName(),
                        user.getPasswordHash()
                )
        );

        // Send register request
        new LoginPost().execute(postObject);
    }

    public static void receiveLoginOrRegister(User.ResponseLogin response) {
        // hide spinner
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(RegisterActivity.class)) {
            ((RegisterActivity) currentActivity).hideProgressDialog();
        } else if (currentActivity.getClass().equals(LoginActivity.class)) {
            ((LoginActivity) currentActivity).hideProgressDialog();
        }

        // Check for null
        if (response == null) {
            Message.showErrorMessage(0);
            return;
        }

        // Check for success
        if (!response.getSuccess()) {
            // could not register
            Message.showErrorMessage(response.getErrorCode(0));
            return; //TODO weitere Fehlerbehandlung
        }

        // Get sessionID
        String sessionID = response.getSessionID();
        if (sessionID == null || sessionID.isEmpty()) {
            // could not receive a session ID
            Message.showErrorMessage(0);
            return;
        }

        // Save the session ID
        com.leruka.leruka.user.User.setSessionID(sessionID);
        // Show Result
        if (currentActivity.getClass().equals(RegisterActivity.class)) {
            // The activity has not changed. Proceed to main menu
            ((RegisterActivity) currentActivity).onReceiveRegister();
        } else if (currentActivity.getClass().equals(LoginActivity.class)) {
            // The activity has not changed. Proceed to main menu
            ((LoginActivity) currentActivity).onReceiveLogin();
        } else if (currentActivity.getClass().equals(GuestMainActivity.class)) {
            // TODO
        }
        else {
            Message.showMessage("Successfully logged in!");
        }
    }

    public static void receiveLogin(User.ResponseLogin response) {
        receiveLoginOrRegister(response);
    }


    private static class LoginPost extends HttpPost<User.ResponseLogin> {

        @Override
        protected User.ResponseLogin CreateResponseObject(InputStream in) {
            try {
                return User.ResponseLogin.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User.ResponseLogin response) {
            receiveLogin(response);
        }
    }

    private static byte[] getLoginBytes(String name, String pass) {
        User.RequestLogin proto = User.RequestLogin.newBuilder()
                .setName(name)
                .setPassword(pass)
                .build();
        return proto.toByteArray();
    }
}
