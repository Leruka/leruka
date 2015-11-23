package com.leruka.leruka.user;

import android.app.Activity;

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
public class Register {

    // Attributes
    private static final String REGISTER_URL = "http://78.46.212.166:8080/leruka/register"; //TODO get from res


    // Methods
    public static void register(String name, String pass1, String pass2) {

        if (!isValid(name, pass1, pass2)) {
            Message.showErrorMessage("Bitte überprüfe deine Eingebe"); //TODO anzeigen, wo der Fehler ist
            return;
        }

        User user = new User(name, User.sha256(pass1));

        try {
            sendRegister(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendRegister(User user) throws IOException {
        PostObject postObject = new PostObject(
                new URL(REGISTER_URL),
                ContentType.json,
                getRegisterJson(
                        user.getUserName(),
                        user.getPasswordHash()
                )
        );

        // Send register request
        new RegisterPost().execute(postObject);

        // Save the user
        User.setCurrentUser(user);
    }

    public static void receiveRegister(ResponseObject response) {

        if (response == null) {
            // Some kind of unknown error
            Message.showErrorMessage("could not read the servers response");
            return;
        }

        Json responseJson = response.getResponseJson();

        if (!response.isSuccess()) {
            // could not register
            Message.showErrorMessage(responseJson.getString("errorMessage"));
            return; //TODO weitere Fehlerbehandlung
        }

        String sessionID = responseJson.getString("sessionID");

        if (sessionID == null || sessionID.isEmpty()) {
            // could not receive a session ID
            Message.showErrorMessage("Could not login, please try again later");
            return;
        }

        // save the session ID
        User.setSessionID(sessionID);

        // Change the activity
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(RegisterActivity.class)) {
            // The activity has not changed. Proceed to main menu
            ((RegisterActivity) currentActivity).onReceiveRegister();
        } else {
            Message.showMessage("Successfully logged in!");
        }
    }

    private static boolean isValid(String name, String pass1, String pass2) {
        // check for null
        if (name == null || pass1 == null) return false;
        if (name.length() < 1 || name.length() > 16) return false;
        if (pass1.length() < 5) return false;
        return (pass2.equals(pass1));
    }

    private static byte[] getRegisterJson(String name, String pass) {
        Json json = new Json();
        json.addAttribute("userName", name);
        json.addAttribute("passwordHash", pass);
        return json.toString().getBytes();
    }

    private static class RegisterPost extends HttpPost {
        @Override
        protected void onPostExecute(ResponseObject result) {
            receiveRegister(result);
        }
    }


}
