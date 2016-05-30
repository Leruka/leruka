package com.leruka.leruka.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class RateLevelsTest {

    @Test
    public void testRateLevel() throws Exception {

    }

    @Test
    public void testGetRating() throws Exception {
        LoginResult res = RateLevels.getRating(1);
        assertEquals(res, new LoginResult(true, null));
    }
}