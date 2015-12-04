package com.leruka.leruka.game.track.creator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.Track;
import com.leruka.leruka.helper.Image;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 03.12.15.
 */
public class TestStage {


    public static Track createTrack(Player player) {

        Bitmap backgroundImage = Image.loadImageWithHeight(R.drawable.test_res,
                Central.getDisplayHeight());
        Background background = new Background(backgroundImage);
        return new Track(background, player);

    }

}
