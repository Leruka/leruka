package com.leruka.leruka.game.track;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by leif on 09.11.15.
 */
public class Obstacle {

    // Attributes
    private Rect rect;
    private Bitmap image;

    // Constructor
    protected Obstacle(Rect rect, Bitmap image) {
        this.rect = rect;
        this.image = image;
    }

    // Methods
    public void draw(Canvas canvas) {
        //TODO
    }
}
