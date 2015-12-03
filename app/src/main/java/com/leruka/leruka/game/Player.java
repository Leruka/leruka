package com.leruka.leruka.game;

import android.graphics.Canvas;

import com.leruka.leruka.game.draw.Animation;
import com.leruka.leruka.helper.Message;

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
        //TODO load images / animations
    }

    // Methods
    protected void jump() {
        Message.showMessage("JUMP!");
        //TODO
    }

    protected void duck() {
        Message.showMessage("DUCK!");
        //TODO
    }

    protected void collide() {
        //TODO
    }

    protected void draw(Canvas canvas) {
        //TODO
    }

    public void update() {
        //TODO
    }


}
