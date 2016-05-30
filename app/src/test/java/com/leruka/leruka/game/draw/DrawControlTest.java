package com.leruka.leruka.game.draw;

import android.content.Context;
import android.view.SurfaceView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class DrawControlTest {

    DrawControl dc;

    @Before
    public void setUp() throws Exception {
        this.dc = new DrawControl();
    }

    @Test
    public void testInitDrawConnection() throws Exception {
        Object object = new String("Hallo");
        dc.initDrawConnection(new SurfaceView((Context) object));
    }
}