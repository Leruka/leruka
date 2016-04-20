package com.leruka.leruka.game.track.creator;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.Track;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.res.ResourceProvider;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 03.12.15.
 */
public class TestStage {


    public static Track createTrack() {

        int groundLevel = Central.getDisplayHeight() - Measure.ph(5);

        // Create Player
        Player p = new Player(groundLevel);

        Bitmap backgroundImage = ResourceProvider.loadImageByHeight(R.drawable.test_bg0,
                Central.getDisplayHeight());
        Background background = new Background(backgroundImage);
        return new Track(background, p);

    }

}
