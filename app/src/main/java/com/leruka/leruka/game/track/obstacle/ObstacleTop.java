package com.leruka.leruka.game.track.obstacle;

import android.graphics.Rect;

import com.leruka.leruka.helper.Measure;

/**
 * Created by leifb on 10.05.16.
 */
public abstract class ObstacleTop extends ObstacleDefault {

    @Override
    protected Rect createRect(int availableWidth, int availableHeight) {

        int height = Measure.ph(this.getHeightPercentage());
        int width = (int) (height * this.getRatio());

        return new Rect(
                availableWidth,
                0,
                availableWidth + width,
                height
        );

    }

}
