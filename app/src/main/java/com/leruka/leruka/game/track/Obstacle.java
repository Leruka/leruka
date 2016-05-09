package com.leruka.leruka.game.track;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.leruka.leruka.helper.Measure;

/**
 * Created by leif on 09.11.15.
 */
public class Obstacle {

    // Attributes
    private Rect rect;
    private Bitmap image;

    private int step;

    // Constructor
    public Obstacle(Rect rect, Bitmap image) {
        this.rect = rect;
        this.image = image;

        this.step = -Measure.pw(.6);
        if (this.step > -1) { this.step = -1; }
    }

    // Methods
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.rect.left, this.rect.top, null);
        // draw rect
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(this.rect, p);
    }

    public void update() {
        // move
        this.rect.offset(this.step,0);
    }

    public boolean isOutOfView() {
        return this.rect.right < 0;
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
