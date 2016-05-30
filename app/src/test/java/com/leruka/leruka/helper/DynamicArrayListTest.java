package com.leruka.leruka.helper;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class DynamicArrayListTest {

    DynamicArrayList dynamicArrayList;
    Iterator iterator;

    @Before
    public void setUp() throws Exception {
        this.dynamicArrayList = new DynamicArrayList();
    }

    @Test
    public void testIterator() throws Exception {
        iterator = dynamicArrayList.iterator();
    }
}