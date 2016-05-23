package com.leruka.leruka.user;

import com.leruka.leruka.R;
import com.leruka.leruka.activity.RateLevelActivity;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.protobuf.Rating;
import com.leruka.protobuf.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by kfrank on 17.05.2016.
 */
public class RateLevels {

    // Attributes
    private static final String RATELEVEL_URL = "http://78.46.212.166:8080/leruka/rate";
    private static final String GET_RATING_URL = "http://78.46.212.166:8080/leruka/getrating";

    public static LoginResult rateLevel(int rating, int levelID) {

        // Check if rate is valid
        if( rating < 0 || rating > 10) {
            return new LoginResult(false, "wrong rating range");
        }

        // Send the request
        try {
            sendRateLevel(rating, levelID);
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);
    }

    public static LoginResult getRating(int levelID) {

        try {
            sendGetRating(levelID);
        } catch (IOException e) {
            return new LoginResult(false, "Es konnte keine Verbindung zum Server hergestellt " +
                    "werden. Bite überprüfe deine Internetverbindung.");
        }

        // When the request has been send, return success
        return new LoginResult(true, null);
    }

    private static void sendRateLevel(int rating, int levelID) throws IOException{
        PostObject postObject = new PostObject(
                new URL(RATELEVEL_URL),
                ContentType.protobuf,
                getRateLevelBytes(
                        LUser.getSessionID(),
                        rating,
                        levelID
                )
        );

        // Send changeSettings request
        new RateLevelPost().execute(postObject);

    }

    private static void sendGetRating(int levelID) throws IOException{
        PostObject postObject = new PostObject(
                new URL(GET_RATING_URL),
                ContentType.protobuf,
                getGetRatingBytes(levelID)
        );

        // Send changeSettings request
        new GetRatingPost().execute(postObject);

    }

    private static byte[] getRateLevelBytes(String sessionID, int rating, int levelID) {
        return Rating.RequestRateLevel.newBuilder()
                .setSessionID(sessionID)
                .setRating(Rating.LevelRating.newBuilder()
                        .setLevelID(levelID)
                        .setRating(rating).build())
                .build().toByteArray();
    }

    private static byte[] getGetRatingBytes(int levelID) {
        return Rating.RequestGetRating.newBuilder()
                .addLevelID(levelID)
                .build().toByteArray();
    }

    private static class RateLevelPost extends HttpPost<Rating.ResponseRateLevel> {
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

    private static class GetRatingPost extends HttpPost<Rating.ResponseGetRating> {
        @Override
        protected Rating.ResponseGetRating CreateResponseObject(InputStream in){
            try {
                return Rating.ResponseGetRating.parseFrom(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Rating.ResponseGetRating response) {
            if (response.getSuccess()) {
                Message.showMessage("Rating: " + response.getRating(0));
            }
            else {
                Message.showErrorMessage("Could not fetch rating :/");
            }
        }
    }

}
