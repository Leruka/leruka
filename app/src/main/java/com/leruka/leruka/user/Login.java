package com.leruka.leruka.user;

import android.app.Activity;
import android.util.Log;

import com.leruka.leruka.activity.GuestMainActivity;
import com.leruka.leruka.activity.LoginActivity;
import com.leruka.leruka.activity.RegisterActivity;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.leruka.net.ResponseObject;

import java.io.IOException;
import java.net.URL;

import de.leifb.objectJson.Json;

/**
 * Created by leif on 09.11.15.
 */
public class Login {

    // Attributes
    private static final String LOGIN_URL = "http://78.46.212.166:8080/leruka/login"; //TODO get from res

    public static void login(String userName, String userPass) {
        // update User
        User user = new User(userName,  User.sha256(userPass));
        User.setCurrentUser(user);

        // validate
        if (!User.isValid()) {

        }


        try {
            sendLogin(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Methods
    protected static void sendLogin(User user) throws IOException {
        PostObject postObject = new PostObject(
                new URL(LOGIN_URL),
                ContentType.json,
                getLoginJson(
                        user.getUserName(),
                        user.getPasswordHash()
                )
        );

        // Send register request
        new LoginPost().execute(postObject);
    }

    public static void receiveLoginOrRegister(ResponseObject response) {
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
        // parse the JSON
        Json responseJson = response.getResponseJson();
        // Check for success
        if (!response.isSuccess()) {
            // could not register
            Message.showErrorMessage(response.getErrorCode());
            return; //TODO weitere Fehlerbehandlung
        }
        // Get sessionID
        String sessionID = responseJson.getString("sessionID");
        if (sessionID == null || sessionID.isEmpty()) {
            // could not receive a session ID
            Message.showErrorMessage(0);
            return;
        }
        // Save the session ID
        User.setSessionID(sessionID);
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

    public static void receiveLogin(ResponseObject response) {
        receiveLoginOrRegister(response);
    }


    private static class LoginPost extends HttpPost {
        @Override
        protected void onPostExecute(ResponseObject result) {
            receiveLogin(result);
        }
    }

    private static byte[] getLoginJson(String name, String pass) {
        Json json = new Json();
        json.addAttribute("userName", name);
        json.addAttribute("passwordHash", pass);
        return json.toString().getBytes();
    }
}
