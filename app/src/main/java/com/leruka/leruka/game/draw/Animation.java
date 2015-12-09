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
    private int currentTime;
    private int[] repeats;
    private int height;
    private int width;

    // Constructor
    public Animation(Bitmap[] frames, int[] repeats) {
        this.frames = frames;
        this.totalFrames = frames.length;
        this.currentFrame = 0;
        this.repeats = repeats;
        this.height = frames[0].getHeight();
        this.width = frames[0].getWidth();
    }

    public Bitmap currentFrame() {
        return this.frames[this.currentFrame];
    }

    public void update() {
        if (currentTime > 0) {
            currentTime--;
        } else {
            currentFrame++;
            if (currentFrame >= totalFrames) currentFrame = 0;
            currentTime = repeats[currentFrame];
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void reset() {
        this.currentFrame = 0;
        this.currentTime = repeats[0];
    }

}
