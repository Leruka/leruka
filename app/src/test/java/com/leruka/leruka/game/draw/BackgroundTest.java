package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;

import com.leruka.leruka.R;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.res.ResourceProvider;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class BackgroundTest {

    Bitmap backgroundImage = ResourceProvider.loadImageByHeight(R.drawable.hintergrund2,
            Central.getDisplayHeight());
    Background bg;

    @Before
    public void setUp() throws Exception {
        bg = new Background(backgroundImage);
    }

    @Test
    public void testUpdate() throws Exception {
        bg.update();
    }

    @Test
    public void testDraw() throws Exception {
        //TODO
    }
}