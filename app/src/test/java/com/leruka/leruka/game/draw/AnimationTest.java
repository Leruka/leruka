package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.res.ResourceProvider;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 18.05.2016.
 */
public class AnimationTest {

    Animation animation;
    Bitmap[] bitmaps = new Bitmap[]{
            ResourceProvider.loadImageByHeight(R.drawable.sprung, Measure.ph(40))
    };

    int[] repeats = new int[]{
            15
    };

    @Before
    public void setUp() {
        this.animation = new Animation(bitmaps, repeats);
    }

    @Test
    public void testCurrentFrame() {
        Bitmap frame = animation.currentFrame();
        assertEquals(frame, bitmaps);
    }

}