package com.leruka.leruka.game.track.obstacle;

import com.leruka.leruka.game.Hitbox;
import com.leruka.leruka.helper.Measure;

/**
 * Created by leifb on 10.05.16.
 */
public abstract class ObstacleTop extends ObstacleDefault {

    @Override
    protected Hitbox createHitbox(int availableWidth, int availableHeight) {

        int height = Measure.ph(this.getHeightPercentage());
        int width = (int) (height * this.getRatio());

        return new Hitbox(
                availableWidth,
                0,
                width,
                height
        );

    }

}
