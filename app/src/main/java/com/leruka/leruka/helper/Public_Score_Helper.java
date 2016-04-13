package com.leruka.leruka.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.leruka.leruka.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruth Weber on 09.12.2015.
 */
public class Public_Score_Helper extends Fragment{

    public Public_Score_Helper() {



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String [] publicScoreArr = {
                "1.  -  Hugo   -  751",
                "2.  -  Peter  -  722",
                "3.  -  Anna   -  699",
                "4.  -  Ludwig -  589",
                "5.  -  Gustav -  490",
                "6.  -  Opa    -  455",
                "7.  -  Oma    -  421",
                "8.  -  Julia  -  375",
                "9.  -  Karl   -  265",
                "10. - Leandro -  255",
                "11. -  Pia    -  248",
                "12. -  Sofia  -  235",
                "13. -  Rolf   -  215",
                "14. -  Silke  -  203",
                "15. - Damaris -  201",
                "16. -  Seline -  200",
                "17. - Florian -  187",
                "18. -  Julian -  185",
                "19. -  Lena   -  164",
                "20. -  Lisa   -  123",
                "21. -  Sabine -  111",
        };

        List<String> publicScore = new ArrayList<>(Arrays.asList(publicScoreArr));

        ArrayAdapter<String> highscoreAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_score,
                        R.id.list_item_highscore_textview,
                        publicScore);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView highscoreListView = (ListView) rootView.findViewById(R.id.listview_highscore);
        highscoreListView.setAdapter(highscoreAdapter);

        return rootView;
    }

}
