package com.leruka.leruka.user;

import android.util.Log;

import com.leruka.leruka.R;
import com.leruka.leruka.activity.ChangeSettingsActivity;
import com.leruka.leruka.activity.RegisterActivity;
import com.leruka.leruka.main.Central;
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
    private static final String CHANGESETTINGS_URL = Central.getCurrentActivity().getResources().getString(R.string.changesetting_url);

    public static LoginResult name(String newName, String oldPass) {
        // Check, if its validName
        String validName = isValidName(newName);
        if (validName != null) {
            return new LoginResult(false, validName);
        }

        // Check old Pass
        if (oldPass == null ||  oldPass.isEmpty()) {
            return new LoginResult(false, Central.getCurrentActivity().getResources().getString(R.string.PasswortEingabeAufforderung));
        }

        try {
            sendChangeSettings(new LUser(newName, null), LUser.sha256(oldPass));
        } catch (IOException e) {
            Log.i(Central.LOG_TAG_MAIN, "IO exception in change settings");
            return new LoginResult(false, Central.getCurrentActivity().getResources().getString(R.string.VerbindungsfehlerServer
            ));
        }

        // When the request has been send, return success
        return new LoginResult(true, null);

    }

    public static LoginResult password(String pw1, String pw2, String oldPass) {

        // Check, if its validPass
        String validPass = isValidPass(pw1, pw2);
        if (validPass != null) {
            return new LoginResult(false, validPass);
        }

        // Check old Pass
        if (oldPass == null ||  oldPass.isEmpty()) {
            return new LoginResult(false, Central.getCurrentActivity().getResources().getString(R.string.PasswortEingabeAufforderung));
        }

        // Send the request
        try {
            sendChangeSettings(new LUser(null, LUser.sha256(pw1)), LUser.sha256(oldPass));
        } catch (IOException e) {
            Log.i(Central.LOG_TAG_MAIN, "IO exception in change settings");
            return new LoginResult(false, Central.getCurrentActivity().getResources().getString(R.string.VerbindungsfehlerServer));
        }

        // When the request has been send, return success
        return new LoginResult(true, null);

    }

    public static LoginResult both(String newName, String newPass1, String newPass2, String oldPass) {
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

        // Check old Pass
        if (oldPass == null ||  oldPass.isEmpty()) {
            Log.i(Central.LOG_TAG_MAIN, "IO exception in change settings");
            return new LoginResult(false, Central.getCurrentActivity().getResources().getString(R.string.PasswortEingabeAufforderung));
        }

        // Send the request
        try {
            sendChangeSettings(new LUser(newName, LUser.sha256(newPass1)), LUser.sha256(oldPass));
        } catch (IOException e) {
            Log.i(Central.LOG_TAG_MAIN, "IO exception in change settings");
            return new LoginResult(false, Central.getCurrentActivity().getResources().getString(R.string.VerbindungsfehlerServer));
        }

        // When the request has been send, return success
        return new LoginResult(true, null);
    }


    private static void sendChangeSettings(LUser user, String oldPass) throws IOException {

        PostObject postObject = new PostObject(
                new URL(CHANGESETTINGS_URL),
                ContentType.protobuf,
                getChangeSettingsBytes(
                        LUser.getSessionID(),
                        user.getUserName(),
                        user.getPasswordHash(),
                        oldPass
                )
        );

        // Send changeSettings request
        new ChangeSettingsPost().execute(postObject);
    }

    private static class ChangeSettingsPost extends HttpPost<User.ResponseChangeSettings> {

        @Override
        protected User.ResponseChangeSettings createResponseObject(InputStream in){
            try {
                return User.ResponseChangeSettings.parseFrom(in);
            } catch (IOException e) {
                Log.i(Central.LOG_TAG_MAIN, "Could not read POST response in change settings");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User.ResponseChangeSettings response) {
            ChangeSettingsActivity.processResponse(response);
        }

    }

    private static byte[] getChangeSettingsBytes(String sessionID, String username, String pass, String oldPass) {
        User.RequestChangeSettings.Builder proto = User.RequestChangeSettings.newBuilder()
                .setSessionID(sessionID)
                .setOldPassword(oldPass);

        if (username != null) {
            proto.setNewName(username);
        }
        if (pass != null) {
            proto.setNewPassword(pass);
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
            return "Bitte gib ein Passwort ein";
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
