package com.leruka.leruka.input;

import android.view.MotionEvent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class GestureTest {

    Gesture gesture;

    @Before
    public void setUp() throws Exception {
        gesture = new Gesture();
    }

    @Test
    public void testProcessGesture() throws Exception {
        MotionEvent me1 = MotionEvent.obtain((long) 5, (long) 9, MotionEvent.ACTION_BUTTON_PRESS, 2.0f, 1.2f, 2);
        MotionEvent me2 = MotionEvent.obtain((long) 5, (long) 9, MotionEvent.ACTION_BUTTON_PRESS, 2.0f, 1.2f, 2);
        gesture.processGesture(me1, me2);
    }
}