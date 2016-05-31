package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by leifb on 27.05.16.
 */
public interface Drawable {

    Bitmap getImage();
    void update();
    Point getSize();

}
