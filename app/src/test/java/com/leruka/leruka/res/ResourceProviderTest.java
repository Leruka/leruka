package com.leruka.leruka.res;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ruth Weber on 30.05.2016.
 */
public class ResourceProviderTest {

    ResourceProvider resourceProvider;

    @Before
    public void setUp() throws Exception {
        resourceProvider = new ResourceProvider();
    }


    @Test
    public void testLoadImageByHeight() throws Exception {
        assertEquals(null, resourceProvider.loadImageByHeight(0, 1));
    }

    @Test
    public void testLoadImageByWidth() throws Exception {
        //TODO
    }

    @Test
    public void testLoadAnimation() throws Exception {
        //TODO
    }
}