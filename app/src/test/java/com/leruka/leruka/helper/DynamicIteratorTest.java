package com.leruka.leruka.helper;

import com.leruka.leruka.game.track.obstacle.QueuedObstacle;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class DynamicIteratorTest {

    List list = new DynamicArrayList();
    List list2 = new DynamicArrayList();
    DynamicIterator dynamicIterator, dynamicIterator2, dynamicIterator3;
    Object object = new Object();

    @Before
    public void setUp() throws Exception {
        list.add(object);
        dynamicIterator = new DynamicIterator(list);
        dynamicIterator2 = new DynamicIterator(list2);
        dynamicIterator3 = new DynamicIterator(list);
    }

    @Test
    public void testHasNext() throws Exception {
        boolean res = dynamicIterator.hasNext();
        assertTrue(res);
        res = dynamicIterator2.hasNext();
        assertFalse(res);
    }

    @Test
    public void testNext() throws Exception {
        dynamicIterator.hasNext();
        Object res = dynamicIterator.next();
        assertEquals(res, object);
        res = dynamicIterator3.next();
        assertEquals(res, object);
        dynamicIterator2.hasNext();
        res = dynamicIterator2.next();
    }

    @Test
    public void testRemove() throws Exception {
        dynamicIterator.remove();
    }
}