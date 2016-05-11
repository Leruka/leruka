package com.leruka.leruka.game.track.obstacle;

import com.leruka.leruka.game.track.obstacle.Obstacle;

/**
 * Created by leifb on 04.05.16.
 */
public class QueuedObstacle {

    private int ticks;
    private Obstacle obstacle;

    public QueuedObstacle(int ticks, Obstacle obstacle) {
        this.ticks = ticks;
        this.obstacle = obstacle;
    }

    /**
     * Counts the tick down by one and checks, if the time has run down.
     * @return true, if the remaining ticks are less than one
     */
    public boolean tick() {
        this.ticks--;
        return this.ticks < 1;
    }

    public Obstacle desolve() {
        return this.obstacle;
    }

}
