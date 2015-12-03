package com.leruka.leruka.game.track.creator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.WindowManager;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.Track;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 03.12.15.
 */
public class TestStage {


    public static Track createTrack(Player player) {

        Point size = new Point();
        Central.getCurrentActivity().getWindowManager().getDefaultDisplay().getSize(size);

        Bitmap backgroundImage = BitmapFactory.decodeResource(Central.getResources(),
                R.drawable.test_background).copy(Bitmap.Config.ARGB_8888, true);
        Background background = new Background(backgroundImage, size.x);
        Track track = new Track(background, player);



        return track;

    }

}
