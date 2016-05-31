package com.leruka.leruka.game.track.obstacle;

import com.leruka.leruka.R;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 10.05.16.
 */
public class ObstacleTop1 extends Obstacle {

    public ObstacleTop1() {
        super(ResourceProvider.loadObstacle(
                R.drawable.hindernissoben1,
                R.integer.image_obstacle_top1_height,
                R.integer.image_obstacle_top1_shift_x,
                R.integer.image_obstacle_top1_shift_y,
                R.integer.box_obstacle_top1_height,
                R.integer.box_obstacle_top1_ratio
        ));

        // Move image
        this.moveTo(Central.getDisplayWidth(), 0);
    }

}
