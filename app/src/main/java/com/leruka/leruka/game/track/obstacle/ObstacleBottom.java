package com.leruka.leruka.game.track.obstacle;

import android.graphics.Rect;

import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.main.Central;

/**
 * Created by leifb on 10.05.16.
 */
public abstract class ObstacleBottom extends ObstacleDefault {

    @Override
    protected Rect createRect(int availableWidth, int availableHeight) {
        int ground = availableHeight - Central.getGroundLevel();
        int height = Measure.ph(this.getHeightPercentage());
        int width = (int) (height * this.getRatio());

        return new Rect(
                availableWidth,
                ground - height,
                availableWidth + width,
                ground
        );
    }

}
