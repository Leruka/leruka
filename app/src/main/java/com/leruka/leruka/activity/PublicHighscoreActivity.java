package com.leruka.leruka.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.leruka.leruka.R;
import com.leruka.leruka.highcore.HighscoreConnection;
import com.leruka.leruka.highcore.Score;
import com.leruka.leruka.main.Central;

import java.util.List;

public class PublicHighscoreActivity extends HighscoreActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // First, make the layout  available to allow refreshing it
        setContentView(R.layout.activity_public_highscore);
        super.onCreate(savedInstanceState);
        HighscoreConnection.getPublicScore();
    }

    public void updateTable() {

        TableLayout table = (TableLayout) findViewById(R.id.public_score_table);
        if (table == null) return;
        table.removeAllViewsInLayout();

        TableRow row = new TableRow(getApplicationContext());
        TextView text;
        // Add Heading
        text = (TextView) this.inflater.inflate(R.layout.fragment_public_score_head, null);
        text.setText(getResources().getString(R.string.rank));
        row.addView(text);

        text = (TextView) this.inflater.inflate(R.layout.fragment_public_score_head, null);
        text.setText(getResources().getString(R.string.name));
        row.addView(text);

        text = (TextView) this.inflater.inflate(R.layout.fragment_public_score_head, null);
        text.setText(getResources().getString(R.string.score));
        row.addView(text);

        table.addView(row);

        for(Score s : this.getScoreList()) {
            TableRow r = new TableRow(getApplicationContext());

            TextView t = (TextView) this.inflater.inflate(R.layout.fragment_public_score_item, null);
            t.setText(Long.toString(s.getRank()));
            r.addView(t);

            t = (TextView) this.inflater.inflate(R.layout.fragment_public_score_item, null);
            t.setText(s.getName());
            r.addView(t);

            t = (TextView) this.inflater.inflate(R.layout.fragment_public_score_item, null);
            t.setText(Long.toString(s.getScore()));
            r.addView(t);

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
