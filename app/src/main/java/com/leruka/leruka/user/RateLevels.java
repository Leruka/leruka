package com.leruka.leruka.user;

import com.leruka.leruka.R;
import com.leruka.leruka.activity.RateLevelActivity;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.protobuf.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by kfrank on 17.05.2016.
 */
public class RateLevels {

    // Attributes
    private static final String RATELEVEL_URL = Central.getCurrentActivity().getResources().getString(R.string.ratelevel_url);
    private static double rate;
    private static String levelName;

    public static LoginResult rateLevel(double rate, String levelName) {

        // Check if rate is valid
        if(rate<0.0 || rate>5.0) {
            return new LoginResult(false, null);
        }

        // get current User
        LUser user = LUser.getCurrentUser();

        // Send the request
        try {
            sendRateLevel(user);
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }


        // When the request has been send, return success
        return new LoginResult(true, null);

    }

    private static void sendRateLevel(LUser user) throws IOException{

        PostObject postObject = new PostObject(
                new URL(RATELEVEL_URL),
                ContentType.protobuf,
                getRateLevelBytes(
                        user.getSessionID(),
                        rate,
                        levelName
                )
        );

        // Send changeSettings request
        new RateLevelPost().execute(postObject);

    }

    private static byte[] getRateLevelBytes(String sessionID, double rate, String levelName) {
        com.leruka.protobuf.Rating.RequestRateLevel proto = com.leruka.protobuf.Rating.RequestRateLevel.newBuilder()
                .setSessionID(sessionID)
                .setRating(rate)
                .setLevelName(levelName)
                .build();
        return proto.toByteArray();
    }

    private static class RateLevelPost extends HttpPost<com.leruka.protobuf.Rating.ResponseRateLevel> {
        @Override
        protected Rating.ResponseRateLevel CreateResponseObject(InputStream in){
            try {
                return Rating.ResponseRateLevel.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Rating.ResponseRateLevel response) {
            RateLevelActivity.processResponse(response);
        }
    }

}
