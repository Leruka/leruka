package com.leruka.leruka.game.track.creator;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.Obstacle;
import com.leruka.leruka.game.track.QueuedObstacle;
import com.leruka.leruka.game.track.background.DefaultBackground;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 04.05.16.
 */
public class Stage1 extends DefaultBackground {

    public Stage1(Player player) {
        super(player);
    }

    @Override
    protected QueuedObstacle createObstacle() {
        // create rect
        int h = Measure.ph(20);
        int w = h;
        Rect r = new Rect();
        r.set(Central.getDisplayWidth(),
                Central.getDisplayHeight() - (h + Measure.ph(5)),
                Central.getDisplayWidth() + w,
                Central.getDisplayHeight() - Measure.ph(5));
        Obstacle o = new Obstacle(r, ResourceProvider.loadImageByWidth(R.drawable.test_hinderniss, w));

        return new QueuedObstacle(this.getRandomTicks(50, 250), o);
    }

    private int getRandomTicks(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
