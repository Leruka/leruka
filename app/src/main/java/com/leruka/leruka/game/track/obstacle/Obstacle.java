package com.leruka.leruka.game.track.obstacle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.leruka.leruka.game.Hitbox;
import com.leruka.leruka.game.draw.Drawable;
import com.leruka.leruka.game.track.Entity;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 09.11.15.
 */
public abstract class Obstacle extends Entity {

    // Attributes
    private Bitmap image;


    // Constructor
    public Obstacle() {
        super(Hitbox.EMPTY, null, null);
        // set settings by implementing class
        this.updateHitbox(this.createHitbox(Central.getDisplayWidth(), Central.getDisplayHeight()));
        this.hitbox.calcHeight();
        this.hitbox.calcWidth();
        this.updateImage(this.loadImage());
    }

    // Methods

    @Override
    public void update() {
        super.update();
        // move hitbox
        this.hitbox.move(Central.getObstacleSpeed(), 0);
    }

    public boolean isOutOfView() {
        return this.hitbox.getX() + this.hitbox.getWidth() < 0;
    }

    protected abstract Hitbox createHitbox(int availableWidth, int availableHeight);

    protected abstract Drawable loadImage();



}
