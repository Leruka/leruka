package com.leruka.leruka.helper;

import com.leruka.leruka.game.track.obstacle.QueuedObstacle;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class DynamicIteratorTest {

    List list = new DynamicArrayList();
    DynamicIterator dynamicIterator, dynamicIterator2;

    @Before
    public void setUp() throws Exception {
        list.add("mnlk");
        dynamicIterator = new DynamicIterator(list);
        dynamicIterator2 = new DynamicIterator(null);
    }

    @Test
    public void testHasNext() throws Exception {
        boolean res = dynamicIterator.hasNext();
        assertTrue(res);
    }

    @Test
    public void testNext() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }
}