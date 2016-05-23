package com.leruka.leruka.user;

import com.leruka.leruka.R;
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
public class Register {

    // Attributes
    private static final String REGISTER_URL = Central.getCurrentActivity().getResources().getString(R.string.register_url); //TODO get from res

    // Methods
    public static LoginResult register(String name, String pass1, String pass2) {

        // Check, if its valid
        String valid = isValid(name, pass1, pass2);
        if (valid != null) {
            return new LoginResult(false, valid);
        }

        // Create and update the user
        LUser user = new LUser(name, LUser.sha256(pass1));
        LUser.setCurrentUser(user);

        // Send register
        try {
            sendRegister(user);
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);
    }

    private static void sendRegister(LUser user) throws IOException {
        PostObject postObject = new PostObject(
                new URL(REGISTER_URL),
                ContentType.protobuf,
                getRegisterBytes(
                        user.getUserName(),
                        user.getPasswordHash()
                )
        );

        // Send register request
        new RegisterPost().execute(postObject);
    }

    private static String isValid(String name, String pass1, String pass2) {
        // check for null
        if (name == null || name.isEmpty()) { return "Bitte gib einen Namen ein"; }
        if (pass1 == null || pass1.isEmpty()) { return "Bitte gib eine Passwort ein"; }
        // Check length
        if (name.length() > 16) { return "Dieser Name ist zu lang"; }
        if (pass1.length() < 4) { return "Dein Passwort muss mindestens vier Zeichen haben"; }
        // Check equal
        if (!pass1.equals(pass2)) { return "Die Passwörter stimmen nicht überein"; }
        // null for valid
        return null;
    }

    private static class RegisterPost extends HttpPost<User.ResponseRegister> {

        @Override
        protected User.ResponseRegister CreateResponseObject(InputStream in) {
            try {
                return User.ResponseRegister.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User.ResponseRegister response) {
            RegisterActivity.processResponse(response);
        }
    }

    private static byte[] getRegisterBytes(String name, String pass) {
        User.RequestRegister proto = User.RequestRegister.newBuilder()
                .setName(name)
                .setPassword(pass)
                .build();
        return proto.toByteArray();
    }

}
