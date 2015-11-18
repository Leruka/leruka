package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;

/**
 * Created by leif on 09.11.15.
 */
public class Animation {

    // Attributes
    private int totalFrames;
    private int currentFrame;
    private Bitmap[] frames;
    private int repeats;

    // Constructor
    public Animation(Bitmap[] frames, int repeats) {
        this.frames = frames;
        this.totalFrames = frames.length;
        this.currentFrame = 0;
        this.repeats = repeats;
    }

    public Bitmap currentFrame() {
        return this.frames[this.currentFrame];
    }

    public void update() {
        //TODO
    }

    public void reset() {
        //TODO
    }

}
