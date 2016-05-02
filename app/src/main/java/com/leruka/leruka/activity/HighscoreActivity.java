package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.leruka.leruka.R;
import com.leruka.leruka.highcore.Score;

import java.util.ArrayList;
import java.util.List;

public abstract class HighscoreActivity extends LerukaActivity {

    private List<Score> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (this.scoreList == null) {
            this.setScoreList(new ArrayList<Score>(0));
        }

        this.updateTable();
    }

    abstract void updateTable();

    protected void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    protected List<Score> getScoreList() {
        return this.scoreList;
    }

    protected void updateScores(List<Score> scoreList) {
        this.setScoreList(scoreList);
        this.updateTable();
    }
}
