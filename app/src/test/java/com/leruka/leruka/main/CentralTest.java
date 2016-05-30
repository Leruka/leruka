package com.leruka.leruka.main;

import android.app.Activity;
import android.content.res.Resources;

import com.leruka.leruka.helper.Measure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ruth Weber on 30.05.2016.
 */
public class CentralTest {

    Central central;
    Resources resources = new Resources(null, null, null);
    Activity activity = new Activity();

    @Before
    public void setUp() throws Exception{
        central = new Central();
    }

    @Test
    public void testGetGroundLevel() throws Exception {
        assertEquals(Measure.ph(5.0), central.getGroundLevel());
    }

    @Test
    public void testGetCurrentActivity() throws Exception {
        assertEquals(null, central.getCurrentActivity());
    }

    @Test
    public void testGetResources() throws Exception {
        assertEquals(null, central.getResources());
    }

    @Test
    public void testGetDisplayWidth() throws Exception {
        assertEquals(0, central.getDisplayWidth());
    }

    @Test
    public void testGetDisplayHeight() throws Exception {
        assertEquals(0, central.getDisplayHeight());
    }

    @Test
    public void testGetObstacleSpeed() throws Exception {
        assertEquals(0, central.getObstacleSpeed());
    }

    @Test
    public void testSetResources() throws Exception {
        central.setResources(resources);
        assertEquals(resources, central.getResources());
    }

    @Test
    public void testSetObstacleSpeed() throws Exception {
        central.setObstacleSpeed(50);
        assertEquals(50, central.getObstacleSpeed());
    }

    @Test
    public void testSetCurrentActivity() throws Exception {
        central.setCurrentActivity(activity);
        assertEquals(activity, central.getCurrentActivity());
    }
}