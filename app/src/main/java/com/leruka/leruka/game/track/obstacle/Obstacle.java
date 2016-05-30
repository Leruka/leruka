package com.leruka.leruka.game.track.obstacle;

import android.graphics.Point;

import com.leruka.leruka.game.Hitbox;
import com.leruka.leruka.game.draw.Drawable;
import com.leruka.leruka.game.track.Entity;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 09.11.15.
 */
public class Obstacle extends Entity {

    private int width;

    // Constructor
    public Obstacle(Entity entity) {
        this(entity.getHitbox(), entity.getImage(), entity.getImageShift());
    }

    public Obstacle(Hitbox hitbox, Drawable image, Point imageShift) {
        super(hitbox, image, imageShift);
        this.hitbox.calcHeight();
        this.hitbox.calcWidth();
        this.width = Math.max(image.getSize().x, this.hitbox.getWidth());
    }

    // Methods

    @Override
    public void update() {
        super.update();
        // move hitbox
        this.hitbox.move(Central.getObstacleSpeed(), 0);
    }

    public boolean isOutOfView() {
        return this.hitbox.getX() + this.width < 0;
    }
}
