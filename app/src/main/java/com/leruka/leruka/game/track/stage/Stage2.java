package com.leruka.leruka.game.track.stage;

import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.track.background.DefaultBackground;
import com.leruka.leruka.game.track.obstacle.*;
import com.leruka.leruka.helper.Measure;

/**
 * Created by leifb on 04.05.16.
 */
public class Stage2 extends DefaultBackground {

    public Stage2(Player player) {
        super(player);
    }

    @Override
    protected QueuedObstacle createObstacle() {
        return new QueuedObstacle(this.getRandomTicks(20, 210), this.getRandomObstacle());
    }

    @Override
    protected int getObstacleSpeed() {
        int speed = -Measure.pw(.6);
        return speed < 0 ? speed : -1;
    }

    private Obstacle getRandomObstacle() {
        switch (this.random.nextInt(2)) {
            case 0:  return new ObstacleBottom1();
            default: return new ObstacleBottom2();
        }
    }

    private int getRandomTicks(int min, int max) {
        return this.random.nextInt(max - min) + min;
    }
}
