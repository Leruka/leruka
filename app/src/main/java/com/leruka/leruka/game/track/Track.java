package com.leruka.leruka.game.track;

import android.graphics.Canvas;

import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.obstacle.Obstacle;
import com.leruka.leruka.game.track.obstacle.QueuedObstacle;
import com.leruka.leruka.main.Central;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by leif on 09.11.15.
 */
public abstract class Track {

    // Attributes
    private Background background;
    private Player player;
    private List<Obstacle> obstacles;
    private Queue<QueuedObstacle> queuedObstacles;
    private int position;
    private int speed;

    public Track(Player player) {
        // Settings from constructor
        this.player = player;

        // Init
        this.position = 0;
        this.obstacles = new ArrayList<>();
        this.queuedObstacles = new LinkedList<>();

        // Abstract settings
        this.background = this.createBackground();
        this.queuedObstacles.add(this.createObstacle());
        Central.setObstacleSpeed(this.getObstacleSpeed());
    }

    // Methods
    public void draw(Canvas canvas) {
        // Draw Track
        this.background.draw(canvas);
        this.player.draw(canvas);
        this.drawObstacles(canvas);
    }

    private void drawObstacles(Canvas canvas) {
        for (Obstacle o : this.obstacles) {
            o.draw(canvas);
        }
    }

    public void update() {
        this.background.update();
        this.player.update();
        this.updateQueuedObstacles();
        this.updateObstacles();
    }

    private void updateQueuedObstacles() {
        if (!this.queuedObstacles.isEmpty() && this.queuedObstacles.peek().tick()) {
            this.dequeueObstacle(this.queuedObstacles.remove());
            this.queuedObstacles.add(this.createObstacle());
        }
    }

    private void dequeueObstacle(QueuedObstacle o) {
        this.queuedObstacles.remove(o);
        this.obstacles.add(o.desolve());
    }

    private void updateObstacles() {
        for (int i = 0; i < this.obstacles.size(); i++) {
            Obstacle o = this.obstacles.get(i);
            o.update();
            if (o.isOutOfView()) {
                this.deleteObstacle(o);
                i--;
            }
        }
    }

    private void deleteObstacle(Obstacle o) {
        this.obstacles.remove(o);
    }

    public Player getPlayer() {
        return this.player;
    }


    protected abstract Background createBackground();

    protected abstract QueuedObstacle createObstacle();

    protected abstract int getObstacleSpeed();

}
