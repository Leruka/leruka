package com.leruka.leruka.game.track.obstacle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class QueuedObstacleTest {

    QueuedObstacle queuedObstacle;
    Obstacle obstacle = new ObstacleBottom1();

    @Before
    public void setUp() throws Exception {
        this.queuedObstacle = new QueuedObstacle(2, obstacle);
    }

    @Test
    public void testTick() throws Exception {
        boolean res = queuedObstacle.tick();
        assertFalse(res);
        res = queuedObstacle.tick();
        assertTrue(res);
    }

    @Test
    public void testDesolve() throws Exception {
        Obstacle res = queuedObstacle.desolve();
        assertEquals(res, obstacle);
    }
}