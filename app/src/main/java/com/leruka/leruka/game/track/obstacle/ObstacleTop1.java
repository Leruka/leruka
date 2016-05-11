package com.leruka.leruka.game.track.obstacle;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 10.05.16.
 */
public class ObstacleTop1 extends ObstacleTop {
    @Override
    protected double getHeightPercentage() {
        return 65;
    }

    @Override
    protected double getRatio() {
        return 1280 / 952;
    }

    @Override
    protected Bitmap loadImage() {
        return ResourceProvider.loadImageByHeight(R.drawable.hindernissoben1, this.rect.height());
    }
}
