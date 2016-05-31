package com.leruka.leruka.sound;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leifb on 31.05.16.
 */
public class MusicTest {

    @Test
    public void testPlayPause() throws Exception {
        Music.pause();
        assertTrue(Music.isPaused());
        assertFalse(Music.isPlaying());
        Music.play();
        assertFalse(Music.isPaused());
    }

    @Test
    public void testSetMute() throws Exception {
        Music.setMute(true);
        assertTrue(Music.isMuted());
        Music.setMute(false);
        assertFalse(Music.isMuted());
    }
}