package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by leif on 09.11.15.
 */
public class Background {

    // Attributes
    private Bitmap image;
    private int position;
    private int length;

    // Constructor
    public Background(Bitmap image) {
        this.image = image;
        this.position = 0;
        this.length = image.getWidth();
    }

    // Methods
    public void update() {
        //TODO
    }

    public void draw(Canvas canvas) {
        //TODO
    }


}
