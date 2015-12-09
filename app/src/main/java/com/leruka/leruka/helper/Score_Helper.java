package com.leruka.leruka.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.leruka.leruka.R;


/**
 * Created by Ruth Weber on 08.12.2015.
 */
public class Score_Helper extends Fragment {

    public Score_Helper() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String [] privateScoreArr = {
                "1. - 751  - 08.12.2015",
                "2. - 650  - 08.12.2015",
                "3. - 629  - 08.12.2015",
                "4. - 458  - 08.12.2015",
                "5. - 350  - 08.12.2015",

        };

        List <String> privateScore = new ArrayList<>(Arrays.asList(privateScoreArr));

        ArrayAdapter <String> highscoreAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_score,
                        R.id.list_item_highscore_textview,
                        privateScore);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView highscoreListView = (ListView) rootView.findViewById(R.id.listview_highscore);
        highscoreListView.setAdapter(highscoreAdapter);

        return rootView;
    }
}
