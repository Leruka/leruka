package com.leruka.leruka.game.track.stage;

import android.graphics.Rect;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.track.obstacle.Obstacle;
import com.leruka.leruka.game.track.obstacle.ObstacleBottom1;
import com.leruka.leruka.game.track.obstacle.QueuedObstacle;
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
        return new QueuedObstacle(this.getRandomTicks(50, 250), new ObstacleBottom1());
    }

    @Override
    protected int getObstacleSpeed() {
        int speed = -Measure.pw(.6);
        return speed < 0 ? speed : -1;
    }

    private int getRandomTicks(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
