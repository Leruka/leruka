package com.leruka.leruka.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.leruka.leruka.R;
import com.leruka.leruka.highcore.HighscoreConnection;
import com.leruka.leruka.highcore.Score;
import com.leruka.leruka.main.Central;

import java.util.ArrayList;
import java.util.List;

public class PublicHighscoreActivity extends HighscoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // First, make the layout  available to allow refreshing it
        setContentView(R.layout.activity_public_highscore);
        super.onCreate(savedInstanceState);
        HighscoreConnection.GetPublicScore();
    }

    public void updateTable() {

        TableLayout table = (TableLayout) findViewById(R.id.public_score_table);
        if (table == null) return;
        table.removeAllViewsInLayout();

        TableRow row = new TableRow(getApplicationContext());
        TextView text;
        // Add Heading
        text = new TextView(getApplicationContext());
        text.setText("Rank");
        row.addView(text);

        text = new TextView(getApplicationContext());
        text.setText("Name");
        row.addView(text);

        text = new TextView(getApplicationContext());
        text.setText("Score");
        row.addView(text);

        table.addView(row);

        for(Score s : this.getScoreList()) {
            TableRow r = new TableRow(getApplicationContext());
            TextView t1 = new TextView(getApplicationContext());
            TextView t2 = new TextView(getApplicationContext());
            TextView t3 = new TextView(getApplicationContext());

            t1.setText(Long.toString(s.getRank()));
            t2.setText(s.getName());
            t3.setText(Long.toString(s.getScore()));

            r.addView(t1);
            r.addView(t2);
            r.addView(t3);
            table.addView(r);
        }
    }

    public static void fillScores(List<Score> scoreList) {
        Activity a = Central.getCurrentActivity();
        if (a.getClass().equals(PublicHighscoreActivity.class)) {
            ((PublicHighscoreActivity) a).updateScores(scoreList);
        }
    }

}
