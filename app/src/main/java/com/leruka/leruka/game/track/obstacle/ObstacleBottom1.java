package com.leruka.leruka.game.track.obstacle;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 09.05.16.
 */
public class ObstacleBottom1 extends Obstacle {

    private static double heightPercentage = 20;
    private static double ratio = 700 / 628;

    @Override
    protected Rect createRect(int availableWidth, int availableHeight) {
        int ground = availableHeight - Central.getGroundLevel();
        int height = Measure.ph(ObstacleBottom1.heightPercentage);
        int width = (int) (height * ObstacleBottom1.ratio);

        return new Rect(
                availableWidth,
                ground - height,
                availableWidth + width,
                ground
        );
    }

    @Override
    protected Bitmap loadImage() {
        return ResourceProvider.loadImageByHeight(R.drawable.hindernissunten1, this.rect.height());
    }
}
