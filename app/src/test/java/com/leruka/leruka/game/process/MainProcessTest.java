package com.leruka.leruka.game.process;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 18.05.2016.
 */
public class MainProcessTest {

    MainProcess mainProcess;

    @Before
    public void setUp() throws Exception {
        this.mainProcess = new MainProcess();
    }

    @Test
    public void testInit() throws Exception {
        mainProcess.init();
    }

    @Test
    public void testRun() throws Exception {
        mainProcess.run();
    }

    @Test
    public void testEnd() throws Exception {
        mainProcess.end();
    }

    @Test
    public void testTick() throws Exception {
        mainProcess.tick();
    }
}