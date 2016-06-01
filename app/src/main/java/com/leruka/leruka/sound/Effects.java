package com.leruka.leruka.sound;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by leif on 09.11.15.
 */
public class Effects {

    // Attributes
    private boolean isMuted;
    private Context context;

    public Effects(Context context) {
        this.context = context;
    }

    // Methods
    public void play(Sound s) {
        if (!isMuted) MediaPlayer.create(this.context, s.getRes()).start();
    }

    public void mute() {
        this.isMuted = true;
    }

    public void unmute()  {
        this.isMuted = false;
    }

}
