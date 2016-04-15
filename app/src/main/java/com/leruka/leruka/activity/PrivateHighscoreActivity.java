package com.leruka.leruka.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.leruka.leruka.R;
import com.leruka.leruka.highcore.HighscoreConnection;
import com.leruka.leruka.highcore.Score;
import com.leruka.leruka.main.Central;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PrivateHighscoreActivity extends LerukaActivity {

    private static List<Score> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_highscore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (PrivateHighscoreActivity.scoreList == null) {
            PrivateHighscoreActivity.scoreList = new ArrayList<>(0);
        }

        this.updateTable();

        HighscoreConnection.GetPrivateScore();

    }

    private void updateTable() {

        TableLayout table = (TableLayout) findViewById(R.id.private_score_table);
        table.removeAllViews();

        TableRow row = new TableRow(getApplicationContext());
        TextView text;
        // Add Heading
        text = new TextView(getApplicationContext());
        text.setText("Rank");
        row.addView(text);

        text = new TextView(getApplicationContext());
        text.setText("Score");
        row.addView(text);

        text = new TextView(getApplicationContext());
        text.setText("Date");
        row.addView(text);

        table.addView(row);

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        for(Score s : PrivateHighscoreActivity.scoreList) {
            TableRow r = new TableRow(getApplicationContext());

            TextView t = new TextView(getApplicationContext());
            t.setText(Long.toString(s.getRank()));
            r.addView(t);

            t = new TextView(getApplicationContext());
            t.setText(Long.toString(s.getScore()));
            r.addView(t);

            t = new TextView(getApplicationContext());
            t.setText(dateFormat.format(s.getDate()));
            r.addView(t);

            table.addView(r);
        }

    }

    public static void fillScores(List<Score> scoreList) {
        PrivateHighscoreActivity.scoreList = scoreList;
        Activity a = Central.getCurrentActivity();
        if (a.getClass().equals(PrivateHighscoreActivity.class)) {
            ((PrivateHighscoreActivity) a).updateTable();
        }
    }

}
