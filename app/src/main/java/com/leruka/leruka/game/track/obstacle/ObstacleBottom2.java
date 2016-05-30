package com.leruka.leruka.game.track.obstacle;


import com.leruka.leruka.R;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 09.05.16.
 */
public class ObstacleBottom2 extends Obstacle {

    public ObstacleBottom2() {
        super(ResourceProvider.loadObstacle(
                R.drawable.hindernissuntern2,
                R.integer.image_obstacle_bottom2_height,
                R.integer.image_obstacle_bottom2_shift_x,
                R.integer.image_obstacle_bottom2_shift_y,
                R.integer.box_obstacle_bottom2_height,
                R.integer.box_obstacle_bottom2_ratio
        ));

        // Move image
        this.moveTo(Central.getDisplayWidth(), Central.getDisplayHeight() - Central.getGroundLevel() - this.hitbox.getHeight());
    }
}
