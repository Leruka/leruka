package com.leruka.leruka.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by leifb on 28.05.16.
 */
public class Hitbox {

    protected final Rect[] rects;
    private Paint paint;

    private int height;
    private int width;

    public Hitbox(Rect... rects) {
        if (rects.length < 1) {
            throw new InvalidParameterException("i need a rect");
        }
        this.rects = rects;
        this.paint = new Paint();
        this.paint.setColor(Color.GREEN);
        this.paint.setStyle(Paint.Style.STROKE);
    }

    public Hitbox(int x, int y, int w, int h) {
        this(new Rect(x, y, x + w, y + h));
    }

    public boolean collides(Hitbox hitbox) {
        for (Rect r : this.rects) {
            if (hitbox.collides(r)) return true;
        }
        return false;
    }

    boolean collides(Rect rect) {
        for (Rect r : this.rects) {
            if (Rect.intersects(r, rect)) return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        for (Rect r : this.rects) {
            canvas.drawRect(r, this.paint);
        }
    }

    public void move(int dx, int dy) {
        for(Rect r : this.rects) {
            r.offset(dx, dy);
        }
    }

    public void moveTo(int x, int y) {
        this.move(
                x - this.getX(),
                y - this.getY()
        );
    }

    public int getX() {
        return this.rects[0].left;
    }

    public int getY() {
        return this.rects[0].top;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void calcHeight() {
        if (this.height != 0) return;
        // find min and max x
        int min = this.rects[0].top;
        int max = this.rects[0].bottom;
        for (int i = 1; i < this.rects.length; i++) {
            if (this.rects[i].top < min) { min = this.rects[i].top; }
            if (this.rects[i].bottom > max) { min = this.rects[i].top; }
        }
        this.height = min < max ? max - min : 0;
    }

    public void calcWidth() {
        if (this.width != 0) return;
        // find min and max x
        int min = this.rects[0].left;
        int max = this.rects[0].right;
        for (int i = 1; i < this.rects.length; i++) {
            if (this.rects[i].left < min) { min = this.rects[i].left; }
            if (this.rects[i].right > max) { min = this.rects[i].right; }
        }
        this.width = min < max ? max - min : 0;
    }

    public static Hitbox EMPTY = new Hitbox(0, 0, 0, 0);

}
