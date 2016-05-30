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
        this.player = new Player();
    }

    @Test
    public void testGetHitbox() {
        Hitbox hitbox = player.getHitbox();
        //Rect rect2 = new Rect();

        assertEquals(hitbox, player.getHitbox());
    }

}