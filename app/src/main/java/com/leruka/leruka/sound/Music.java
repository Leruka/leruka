package com.leruka.leruka.sound;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by leif on 09.11.15.
 */
public class Music {

    // Attributes
    private static boolean isPlaying;
    private static boolean isPaused;
    private static boolean isMuted;
    private static boolean isInitialized;

    private static MediaPlayer musicPlayer;

    static {
        isPlaying = false;
        isPaused = false;
        isMuted = false;
        isInitialized = false;
    }

    // Hide constructor
    private Music() { }

    public static void init(Context context, Sound sound, boolean play) {
        if (Music.isInitialized)
            Music.musicPlayer.release();
        else
            Music.isInitialized = true;
        Music.musicPlayer = MediaPlayer.create(context, sound.getRes());
        Music.updateMute();
        Music.musicPlayer.setLooping(true);
        if (play) Music.play();
    }

    public static boolean isPlaying() {
        return isPlaying;
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public static boolean isMuted() {
        return isMuted;
    }

    // Methods
    public static void play() {
        Music.isPaused = false;
        if (Music.isInitialized) {
            Music.isPlaying = true;
            Music.musicPlayer.start();
        }
    }

    public static void pause() {
        Music.isPaused = true;
        Music.isPlaying = false;
        if (Music.isInitialized)
            Music.musicPlayer.pause();
    }

    public static void stop() {
        if (Music.isInitialized)
            Music.musicPlayer.stop();
    }

    public static void setMute(boolean isMuted) {
        Music.isMuted = isMuted;
        if (Music.isInitialized) {
            Music.updateMute();
        }
    }

    private static void updateMute() {
        if (Music.isMuted)
            Music.musicPlayer.setVolume(0,0);
        else
            Music.musicPlayer.setVolume(1,1);
    }


}

