package com.leruka.leruka.helper;

import android.view.Display;

import com.leruka.leruka.main.Central;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ruth Weber on 30.05.2016.
 */
public class MeasureTest {

    Measure measure = new Measure();
    Display display;

    @Before
    public void setUp() throws Exception {
        measure = new Measure();
    }

    @Test
    public void testPh(){
        assertEquals(measure.ph(100), Central.getDisplayHeight());
    }

    @Test
    public void testPw(){
        assertEquals(measure.pw(100), Central.getDisplayWidth());
    }

}