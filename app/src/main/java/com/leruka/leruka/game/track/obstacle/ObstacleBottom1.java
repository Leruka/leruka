package com.leruka.leruka.game.track.obstacle;

import com.leruka.leruka.R;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 09.05.16.
 */
public class ObstacleBottom1 extends Obstacle {

    public ObstacleBottom1() {
        super(ResourceProvider.loadObstacle(
                R.drawable.hindernissunten1,
                R.integer.image_obstacle_bottom1_height,
                R.integer.image_obstacle_bottom1_shift_x,
                R.integer.image_obstacle_bottom1_shift_y,
                R.integer.box_obstacle_bottom1_height,
                R.integer.box_obstacle_bottom1_ratio
        ));

        // Move image
        this.moveTo(Central.getDisplayWidth(), Central.getDisplayHeight() - Central.getGroundLevel() - this.hitbox.getHeight());
    }
}
