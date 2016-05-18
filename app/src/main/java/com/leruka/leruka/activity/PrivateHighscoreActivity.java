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

public class PrivateHighscoreActivity extends HighscoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // First, make the layout available to allow refreshing it
        setContentView(R.layout.activity_private_highscore);
        super.onCreate(savedInstanceState);
        HighscoreConnection.GetPrivateScore();
    }

    public void updateTable() {

        TableLayout table = (TableLayout) findViewById(R.id.private_score_table);
        if (table == null) return;
        table.removeAllViews();

        TableRow row = new TableRow(getApplicationContext());
        TextView text;
        // Add Heading
        text = (TextView) this.inflater.inflate(R.layout.fragment_public_score_head, null);
        text.setText("Rank");
        row.addView(text);

        text = (TextView) this.inflater.inflate(R.layout.fragment_public_score_head, null);
        text.setText("Score");
        row.addView(text);

        text = (TextView) this.inflater.inflate(R.layout.fragment_public_score_head, null);
        text.setText("Date");
        row.addView(text);

        table.addView(row);

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        for(Score s : this.getScoreList()) {
            TableRow r = new TableRow(getApplicationContext());

            TextView t = (TextView) this.inflater.inflate(R.layout.fragment_public_score_item, null);
            t.setText(Long.toString(s.getRank()));
            r.addView(t);

            t = (TextView) this.inflater.inflate(R.layout.fragment_public_score_item, null);
            t.setText(Long.toString(s.getScore()));
            r.addView(t);

            t = (TextView) this.inflater.inflate(R.layout.fragment_public_score_item, null);
            t.setText(dateFormat.format(s.getDate()));
            r.addView(t);

            table.addView(r);
        }

    }

    public static void fillScores(List<Score> scoreList) {
        Activity a = Central.getCurrentActivity();
        if (a.getClass().equals(PrivateHighscoreActivity.class)) {
            ((PrivateHighscoreActivity) a).updateScores(scoreList);
        }
    }

}
