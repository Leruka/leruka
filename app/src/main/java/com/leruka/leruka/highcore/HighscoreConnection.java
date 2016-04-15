package com.leruka.leruka.highcore;

import com.leruka.leruka.activity.PublicHighscoreActivity;
import com.leruka.leruka.net.HttpGet;
import com.leruka.protobuf.Highscore;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leif on 13.04.16.
 */
public class HighscoreConnection {

    private static final String PUBLIC_SCORE_URL = "http://78.46.212.166:8080/leruka/score"; //TODO get from res

    public static void GetPublicScore() {
        new PublicScoreGet().execute(PUBLIC_SCORE_URL);
    }

    private static class PublicScoreGet extends HttpGet<Highscore.ResponseScores> {

        @Override
        protected void onPostExecute(Highscore.ResponseScores response) {
            //TODO currently only a LoginResponse is returned. switch to RegisterResponse!
            if (response != null) {
                // transform protobuf into score list
                List<Score> scores = new ArrayList<>(response.getScoresCount());
                for (Highscore.Score s : response.getScoresList()) {
                    scores.add(new Score(
                            s.getUserName(),
                            s.getScore(),
                            s.getRank()
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

}
