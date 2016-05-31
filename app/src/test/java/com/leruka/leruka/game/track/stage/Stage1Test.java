package com.leruka.leruka.game.track.stage;

import com.leruka.leruka.game.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class Stage1Test {

    Stage1 stage1;
    Player player = new Player();

    @Before
    public void setUp() throws Exception {
        this.stage1 = new Stage1(player);
    }

    @Test
    public void testGetObstacleSpeed() throws Exception {
        stage1.getObstacleSpeed();
    }
}