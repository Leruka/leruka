package com.leruka.leruka.game.track.stage;

import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.track.background.DefaultBackground;
import com.leruka.leruka.game.track.obstacle.Obstacle;
import com.leruka.leruka.game.track.obstacle.ObstacleBottom1;
import com.leruka.leruka.game.track.obstacle.ObstacleBottom2;
import com.leruka.leruka.game.track.obstacle.ObstacleTop1;
import com.leruka.leruka.game.track.obstacle.ObstacleTop2;
import com.leruka.leruka.game.track.obstacle.QueuedObstacle;
import com.leruka.leruka.helper.Measure;

/**
 * Created by leifb on 04.05.16.
 */
public class Stage1 extends DefaultBackground {

    public Stage1(Player player) {
        super(player);
    }

    @Override
    protected QueuedObstacle createObstacle() {
        return new QueuedObstacle(this.getRandomTicks(50, 300), this.getRandomObstacle());
    }

    @Override
    protected int getObstacleSpeed() {
        int speed = -Measure.pw(.6);
        return speed < 0 ? speed : -1;
    }

    private Obstacle getRandomObstacle() {
        switch (this.random.nextInt(4)) {
            case 0:  return new ObstacleBottom1();
            case 1:  return new ObstacleBottom2();
            case 2:  return new ObstacleTop1();
            default: return new ObstacleTop2();
        }
    }

    private int getRandomTicks(int min, int max) {
        return this.random.nextInt(max - min) + min;
    }
}
