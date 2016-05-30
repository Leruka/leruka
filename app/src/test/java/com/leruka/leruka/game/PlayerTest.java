package com.leruka.leruka.game;

import android.graphics.Rect;

import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.main.Central;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 18.05.2016.
 */
public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        this.player = new Player(Central.getDisplayHeight() - Measure.ph(5));
    }

    @Test
    public void testGetHitbox() {
        Rect rect1 = player.getHitbox();
        //Rect rect2 = new Rect();

        assertEquals(rect1, player.getHitbox());
    }

}