package com.leruka.leruka.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Score_Helper;

public class PrtivateHighscoreActivity extends LerukaActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prtivate_highscore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listview = (ListView) findViewById(R.id.listview_highscore);

    }

}
