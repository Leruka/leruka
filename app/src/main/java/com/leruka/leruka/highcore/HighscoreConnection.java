package com.leruka.leruka.highcore;

import android.util.Log;

import com.leruka.leruka.R;
import com.leruka.leruka.activity.PrivateHighscoreActivity;
import com.leruka.leruka.activity.PublicHighscoreActivity;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.net.ContentType;
import com.leruka.leruka.net.HttpGet;
import com.leruka.leruka.net.HttpPost;
import com.leruka.leruka.net.PostObject;
import com.leruka.leruka.user.LUser;
import com.leruka.protobuf.Highscore;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by leif on 13.04.16.
 */
public class HighscoreConnection {

    private static final String PUBLIC_SCORE_URL = Central.getCurrentActivity().getResources().getString(R.string.publicHighscore_url);
    private static final String PRIVATE_SCORE_URL = Central.getCurrentActivity().getResources().getString(R.string.privateHighscore_url);

    public static void getPublicScore() {
        new PublicScoreGet().execute(PUBLIC_SCORE_URL);
    }

    public static void getPrivateScore() {
        // Create the request object
        Highscore.RequestPrivateScore requestObject = Highscore.RequestPrivateScore.newBuilder()
                .setSessionID(LUser.getSessionID()).build();

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
        protected Highscore.ResponseScores createResponseObject(InputStream in) {
            try {
                return Highscore.ResponseScores.parseFrom(in);
            } catch (IOException e) {
                Log.i(Central.LOG_TAG_MAIN, e.getMessage());
                Message.showErrorMessage("Communication with the server failed, please try again later!");
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
                            LUser.getCurrentUser().getUserName(),
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
        protected Highscore.ResponseScores createResponseObject(InputStream in) {
            try {
                return Highscore.ResponseScores.parseFrom(in);
            } catch (IOException e) {
                Log.i(Central.LOG_TAG_MAIN, e.getMessage());
                Message.showErrorMessage("Communication with the server failed, please try again later!");
                return null;
            }
        }
    }

}
