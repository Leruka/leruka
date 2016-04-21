package com.leruka.leruka.user;

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
    private static final String REGISTER_URL = "http://78.46.212.166:8080/leruka/register"; //TODO get from res


    // Methods
    public static void register(String name, String pass1, String pass2) {

        if (!isValid(name, pass1, pass2)) {
            Message.showErrorMessage("Bitte überprüfe deine Eingebe"); //TODO anzeigen, wo der Fehler ist
            ((RegisterActivity) Central.getCurrentActivity()).hideProgressDialog();
            return;
        }

        LUser user =
                new LUser(name, LUser.sha256(pass1));

        try {
            sendRegister(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        // Save the user
        LUser.setCurrentUser(user);
    }

    public static void receiveRegister(User.ResponseRegister response) {
        //TODO check successful register

        Login.receiveLoginOrRegister(response.getLogin());
    }

    private static boolean isValid(String name, String pass1, String pass2) {
        // check for null
        if (name == null || pass1 == null) return false;
        if (name.length() < 1 || name.length() > 16) return false;
        if (pass1.length() < 5) return false;
        return (pass2.equals(pass1));
    }

    private static byte[] getRegisterBytes(String name, String pass) {
        User.RequestRegister proto = User.RequestRegister.newBuilder()
                .setName(name)
                .setPassword(pass)
                .build();
        return proto.toByteArray();
    }

    private static class RegisterPost extends HttpPost<User.ResponseLogin> {

        @Override
        protected void onPostExecute(User.ResponseLogin response) {
            //TODO currently only a LoginResponse is returned. switch to RegisterResponse!
            if (response != null) {
                Login.receiveLoginOrRegister(response);
            }
            else {
                //TODO
            }
        }

        @Override
        protected User.ResponseLogin CreateResponseObject(InputStream in) {
            try {
                return User.ResponseLogin.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
