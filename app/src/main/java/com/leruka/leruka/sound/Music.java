package com.leruka.leruka.sound;

import android.media.MediaPlayer;

import com.leruka.leruka.main.Central;

/**
 * Created by leif on 09.11.15.
 */
public class Music {

    // Attributes
    private static boolean isPlaying;
    private static boolean isPaused;
    private static boolean isMuted;

    private static MediaPlayer backgroundmusic;

    //Constructor
    public Music(MediaPlayer backgroundmusic) {
        this.backgroundmusic = backgroundmusic;
        //backgroundmusic = MediaPlayer.create(Central.getCurrentActivity(), R.raw.music);

        backgroundmusic.setLooping(true);
        play(backgroundmusic);
    }

    // Methods
    public static void play(MediaPlayer sound) {
        //TODO
        sound.start();

    }

    public static void pause() {
        //TODO
        backgroundmusic.release();
        Central.getCurrentActivity().finish();
    }

    public static void stop() {
        //TODO
    }

    public static void resume() {
        //TODO
    }

    public static void setMute(boolean isMuted) {
        //TODO
        Music.isMuted = isMuted;
    }


}

