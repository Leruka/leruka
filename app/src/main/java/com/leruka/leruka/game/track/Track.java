package com.leruka.leruka.game.track;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.leruka.leruka.activity.GameMainActivity;
import com.leruka.leruka.game.Game;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.obstacle.Obstacle;
import com.leruka.leruka.game.track.obstacle.QueuedObstacle;
import com.leruka.leruka.helper.DynamicArrayList;
import com.leruka.leruka.main.Central;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by leif on 09.11.15.
 */
public abstract class Track {

    // Attributes
    private Background background;
    private Player player;
    private List<Obstacle> obstacles;
    private Queue<QueuedObstacle> queuedObstacles;
    private Paint scorePaint;
    private int afterColisionTimer;

    protected Random random;

    public Track(Player player) {
        // Settings from constructor
        this.player = player;

        // Init
        this.obstacles = new DynamicArrayList<>();
        this.queuedObstacles = new LinkedList<>();
        this.random = new Random();
        this.afterColisionTimer = 150;

        // Abstract settings
        this.background = this.createBackground();
        this.queuedObstacles.add(this.createObstacle());
        Central.setObstacleSpeed(this.getObstacleSpeed());

        this.scorePaint = new Paint();
        this.scorePaint.setColor(Color.RED);
        this.scorePaint.setStyle(Paint.Style.FILL);
        this.scorePaint.setAntiAlias(true);
        this.scorePaint.setTextSize(40f);
    }

    // Methods
    public void draw(Canvas canvas) {
        // Draw Track
        this.background.draw(canvas);
        this.player.draw(canvas);
        this.drawObstacles(canvas);
        this.drawCounter(canvas);
    }

    private void drawCounter(Canvas canvas) {
        canvas.drawText(Game.getCounter(),Central.getDisplayWidth() - 200,100, scorePaint);

    }

    private void drawObstacles(Canvas canvas) {
        for (Obstacle o : this.obstacles) {
            o.draw(canvas);
        }
    }

    public void update() {
        if (this.player.hasCollided()) {
            this.updateAfterCollision();
        }
        else this.updateDefault();

    }

    private void updateDefault() {
        this.background.update();
        this.player.update();
        this.updateQueuedObstacles();
        this.updateObstacles();

        // Check for collision
        for (Obstacle o : this.obstacles) {
            if (o.intersects(player)) {
                this.player.collide();
                return;
            }
        }
    }

    private void updateAfterCollision() {
        this.player.update();
        this.afterColisionTimer--;
        if (this.afterColisionTimer <= 0) {
            Game.gameOver();
        }
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
