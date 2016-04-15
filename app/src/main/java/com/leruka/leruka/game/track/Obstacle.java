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
        canvas.drawBitmap(image, this.rect.left, this.rect.top, null);
    }

    public boolean isCollide(Rect player_rect) {
        if (rect.intersect(player_rect)) {
            return true;
        }
        return false;
    }

    public void move () {
        //TODO: Bewegung random
        this.rect.set(5, 5, 5, 5);
    }


}
