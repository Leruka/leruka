package com.leruka.leruka.game.process;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 18.05.2016.
 */
public class DrawProcessTest {

    DrawProcess drawProcess;

    @Before
    public void setUp() {
        this.drawProcess = new DrawProcess();
    }

    @Test
    public void testInit() throws Exception {
        drawProcess.init();
    }

    @Test
    public void testRun() throws Exception {
        drawProcess.run();
    }

    @Test
    public void testEnd() throws Exception {
        drawProcess.end();
    }
}