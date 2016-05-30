package com.leruka.leruka.game.track.obstacle;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.game.draw.Drawable;
import com.leruka.leruka.game.draw.Image;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 09.05.16.
 */
public class ObstacleBottom1 extends ObstacleBottom {

    @Override
    protected Drawable loadImage() {
        return new Image(ResourceProvider.loadImageByHeight(R.drawable.hindernissunten1, this.hitbox.getHeight()));
    }

    @Override
    protected double getHeightPercentage() {
        return 20;
    }

    @Override
    protected double getRatio() {
        return 700 / 628;
    }
}
