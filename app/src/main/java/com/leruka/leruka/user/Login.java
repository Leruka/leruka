package com.leruka.leruka.user;

import android.app.Activity;

import com.leruka.leruka.activity.LoginActivity;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;

import java.io.IOException;
import java.net.MalformedURLException;
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

    public static void receiveLogin(String result) {
        //TODO parse json

        Message.showMessage(result);

        // everything is fine
        // User.setSessionID(session);

        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(LoginActivity.class)) {
            // The activity has not changed. Proceed to main menu
            ((LoginActivity) currentActivity).onReceiveLogin();
        } else {
            Message.showMessage("Successfully logged in!");
        }
    }


    private static class LoginPost extends HttpPost {
        @Override
        protected void onPostExecute(String result) {
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
