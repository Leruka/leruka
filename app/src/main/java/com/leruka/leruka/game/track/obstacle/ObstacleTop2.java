package com.leruka.leruka.game.track.obstacle;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.game.draw.Drawable;
import com.leruka.leruka.game.draw.Image;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 10.05.16.
 */
public class ObstacleTop2 extends ObstacleTop {
    @Override
    protected double getHeightPercentage() {
        return 65;
    }

    @Override
    protected double getRatio() {
        return 1428 / 1256;
    }

    @Override
    protected Drawable loadImage() {
        return new Image(ResourceProvider.loadImageByHeight(R.drawable.hindernissoben2, this.hitbox.getHeight()));
    }
}
