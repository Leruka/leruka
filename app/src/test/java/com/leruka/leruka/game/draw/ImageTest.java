package com.leruka.leruka.game.draw;

import android.graphics.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leifb on 31.05.16.
 */
public class ImageTest {

    private Image image;

    @Before
    public void setUp() throws Exception {
        this.image = new Image(null);
    }

    @Test
    public void testGetImage() throws Exception {
        assertEquals(this.image.getImage(), null);
    }

    @Test
    public void testUpdate() throws Exception {
        this.image.update();
    }

    @Test(expected = NullPointerException.class)
    public void testGetSize() throws Exception {
        assertEquals(this.image.getSize(), new Point(0,0));
    }
}