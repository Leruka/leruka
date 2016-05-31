package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by leifb on 27.05.16.
 */
public class Image implements Drawable{

    private Bitmap bitmap;

    public Image(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    @Override
    public Bitmap getImage() {
        return this.bitmap;
    }

    @Override
    public void update() {
        // Do nothing
    }

    @Override
    public Point getSize() {
        return new Point(this.bitmap.getWidth(), this.bitmap.getHeight());
    }
}
