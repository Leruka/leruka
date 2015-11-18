package com.leruka.leruka.game;

import android.graphics.Canvas;

import com.leruka.leruka.game.draw.Animation;

/**
 * Created by leif on 09.11.15.
 */
public class Player {

    // Attributes
    private boolean isJumping;
    private boolean isDucking;
    private Animation currentAnimation;

    // Constructor
    public Player() {
        //TODO
    }

    // Methods
    protected void jump() {
        //TODO
        Animation a = new Animation(null, 3);
    }

    protected void duck() {
        //TODO
    }

    protected void collide() {
        //TODO
    }

    protected void draw(Canvas canvas) {
        //TODO
    }


}
