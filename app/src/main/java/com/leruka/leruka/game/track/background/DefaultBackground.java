package com.leruka.leruka.game.track.background;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;
import com.leruka.leruka.game.track.Track;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

/**
 * Created by leifb on 09.05.16.
 */
public abstract class DefaultBackground extends Track{

    public DefaultBackground(Player player) {
        super(player);
    }

    @Override
    protected Background createBackground() {
        Bitmap backgroundImage = ResourceProvider.loadImageByHeight(R.drawable.hintergrund2,
                Central.getDisplayHeight());
        return new Background(backgroundImage);
    }

}
