package com.leruka.leruka.highcore;

import com.leruka.leruka.activity.PrivateHighscoreActivity;
import com.leruka.leruka.activity.PublicHighscoreActivity;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpGet;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.leruka.user.User;
import com.leruka.protobuf.Highscore;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by leif on 13.04.16.
 */
public class HighscoreConnection {

    private static final String PUBLIC_SCORE_URL = "http://78.46.212.166:8080/leruka/score"; //TODO get from res
    private static final String PRIVATE_SCORE_URL = "http://78.46.212.166:8080/leruka/privatescore"; //TODO get from res

    public static void GetPublicScore() {
        new PublicScoreGet().execute(PUBLIC_SCORE_URL);
    }

    public static void GetPrivateScore() {
        // Create the request object
        Highscore.RequestPrivateScore requestObject = Highscore.RequestPrivateScore.newBuilder()
                .setSessionID(User.getSessionID()).build();

        // Create Post Object
        try {
            PostObject postObject = new PostObject(
                    new URL(PRIVATE_SCORE_URL),
                    ContentType.protobuf,
                    requestObject.toByteArray());
            new PrivateScorePost().execute(postObject);
        } catch (MalformedURLException e) {
            /* this will not happen */
        }
    }

    private static class PublicScoreGet extends HttpGet<Highscore.ResponseScores> {

        @Override
        protected void onPostExecute(Highscore.ResponseScores response) {

            if (response != null) {
                // transform protobuf into score list
                List<Score> scores = new ArrayList<>(response.getScoresCount());
                for (Highscore.Score s : response.getScoresList()) {
                    scores.add(new Score(
                            s.getUserName(),
                            s.getScore(),
                            s.getRank(),
                            null
                    ));
                }
                PublicHighscoreActivity.fillScores(scores);
            }
            else {
                //TODO
            }
        }

        @Override
        protected Highscore.ResponseScores CreateResponseObject(InputStream in) {
            try {
                return Highscore.ResponseScores.parseFrom(in);
            } catch (IOException e) {
                //TODO
                e.printStackTrace();
                return null;
            }
        }
    }

    private static class PrivateScorePost extends HttpPost<Highscore.ResponseScores> {

        @Override
        protected void onPostExecute(Highscore.ResponseScores response) {
            if (response != null) {
                // transform protobuf into score list
                List<Score> scores = new ArrayList<>(response.getScoresCount());
                for (Highscore.Score s : response.getScoresList()) {
                    scores.add(new Score(
                            User.getCurrentUser().getUserName(),
                            s.getScore(),
                            s.getRank(),
                            new Date(s.getTimestamp())
                    ));
                }
                PrivateHighscoreActivity.fillScores(scores);
            }
            else {
                //TODO
            }
        }

        @Override
        protected Highscore.ResponseScores CreateResponseObject(InputStream in) {
            try {
                return Highscore.ResponseScores.parseFrom(in);
            } catch (IOException e) {
                //TODO
                e.printStackTrace();
                return null;
            }
        }
    }

}
