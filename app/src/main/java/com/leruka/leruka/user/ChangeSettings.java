package com.leruka.leruka.user;

import com.leruka.leruka.activity.ChangeSettingsActivity;
import com.leruka.leruka.activity.RegisterActivity;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.protobuf.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by kfrank on 11.05.2016.
 */
public class ChangeSettings {

    // Attributes
    private static final String CHANGESETTINGS_URL = "http://78.46.212.166:8080/leruka/changesettings";

    public static LoginResult name(String newName) {
        // Check, if its validName
        String validName = isValidName(newName);
        if (validName != null) {
            return new LoginResult(false, validName);
        }

        try {
            sendChangeSettings(new LUser(newName, null));
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);

    }

    public static LoginResult password(String pw1, String pw2) {

        // Check, if its validPass
        String validPass = isValidPass(pw1, pw2);
        if (validPass != null) {
            return new LoginResult(false, validPass);
        }

        // Send the request
        try {
            sendChangeSettings(new LUser(null, LUser.sha256(pw1)));
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);

    }

    public static LoginResult both(String newName, String newPass1, String newPass2) {
        // Check, if its validName
        String validName = isValidName(newName);
        if (validName != null) {
            return new LoginResult(false, validName);
        }

        // Check, if its validPass
        String validPass = isValidPass(newPass1, newPass2);
        if (validPass != null) {
            return new LoginResult(false, validPass);
        }

        // Send the request
        try {
            sendChangeSettings(new LUser(newName, LUser.sha256(newPass1)));
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);
    }


    private static void sendChangeSettings(LUser user) throws IOException{

        PostObject postObject = new PostObject(
                new URL(CHANGESETTINGS_URL),
                ContentType.protobuf,
                getChangeSettingsBytes(
                        LUser.getSessionID(),
                        user.getUserName(),
                        user.getPasswordHash()
                )
        );

        // Send changeSettings request
        new ChangeSettingsPost().execute(postObject);
    }

    private static class ChangeSettingsPost extends HttpPost<User.ResponseChangeSettings> {

        @Override
        protected User.ResponseChangeSettings CreateResponseObject(InputStream in){
            try {
                return User.ResponseChangeSettings.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User.ResponseChangeSettings response) {
            ChangeSettingsActivity.processResponse(response);
        }

    }

    private static byte[] getChangeSettingsBytes(String sessionID, String username, String pass) {
        User.RequestChangeSettings.Builder proto = User.RequestChangeSettings.newBuilder()
                .setSessionID(sessionID);

        if (username != null) {
            proto.setName(username);
        }
        if (pass != null) {
            proto.setPassword(pass);
        }

        return proto.build().toByteArray();
    }

    private static String isValidName(String name) {
        // check for null
        if (name == null || name.isEmpty()) { return "Bitte gib einen Namen ein"; }
        // Check length
        if (name.length() > 16) { return "Dieser Name ist zu lang"; }
        // null for valid
        return null;
    }

    private static String isValidPass(String pw1, String pw2) {
        // check for null
        if (pw1 == null || pw1.isEmpty()) {
            return "Bitte gib eine Passwort ein";
        }
        // Check length
        if (pw1.length() < 4) {
            return "Dein Passwort muss mindestens vier Zeichen haben";
        }
        // Check equal
        if (!pw1.equals(pw2)) {
            return "Die Passwörter stimmen nicht überein";
        }
        // null for valid
        return null;
    }
}
