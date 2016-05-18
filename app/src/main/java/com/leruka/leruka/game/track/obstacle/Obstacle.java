package com.leruka.leruka.game.track.obstacle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 09.11.15.
 */
public abstract class Obstacle {

    // Attributes
    protected Rect rect;
    private Bitmap image;
    private boolean isColliding;


    // Constructor
    public Obstacle() {
        // set settings by implementing class
        this.rect = this.createRect(Central.getDisplayWidth(), Central.getDisplayHeight());
        this.image = this.loadImage();
    }

    // Methods
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.rect.left, this.rect.top, null);
        // draw rect
        if (this.isColliding) {
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(this.rect, p);
        }
    }

    public void update() {
        // move
        this.rect.offset(Central.getObstacleSpeed(), 0);
    }

    public boolean isOutOfView() {
        return this.rect.right < 0;
    }

    public boolean intersects(Rect rect) {
        this.isColliding = Rect.intersects(this.rect, rect);
        return this.isColliding;
    }



    protected abstract Rect createRect(int availableWidth, int availableHeight);

    protected abstract Bitmap loadImage();



}
