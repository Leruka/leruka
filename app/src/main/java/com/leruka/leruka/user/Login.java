package com.leruka.leruka.user;

import android.app.Activity;

import com.leruka.leruka.R;
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
    private static final String LOGIN_URL = Central.getCurrentActivity().getResources().getString(R.string.login_url);

    public static LoginResult login(String userName, String userPass) {

        // Check for null / ws
        if (userName == null || userName.isEmpty()) {
            return new LoginResult(false, "Bitte gib ein Benutzer an");
        }
        else if (userPass == null || userPass.isEmpty()) {
            return new LoginResult(false, "Bitte gib ein Passwort an");
        }

        // update User
        LUser user = new LUser(userName,  LUser.sha256(userPass));
        LUser.setCurrentUser(user);

        // Send the request
        try {
            sendLogin(user);
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);
    }



    // Methods
    protected static void sendLogin(LUser user) throws IOException {
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

    private static class LoginPost extends HttpPost<User.ResponseLogin> {

        @Override
        protected User.ResponseLogin createResponseObject(InputStream in) {
            try {
                return User.ResponseLogin.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User.ResponseLogin response) {
            LoginActivity.processResponse(response);
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
