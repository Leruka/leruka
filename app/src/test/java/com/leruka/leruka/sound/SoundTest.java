package com.leruka.leruka.sound;

import com.leruka.leruka.R;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leifb on 31.05.16.
 */
public class SoundTest {

    @Test
    public void testGetRes() throws Exception {
        Sound s = Sound.Theme;
        assertEquals(s.getRes(), R.raw.music);
    }
}