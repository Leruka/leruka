package com.leruka.leruka.sound;

import com.leruka.leruka.R;

/**
 * Created by leif on 09.11.15.
 */
public enum Sound {

    // Music
    Theme(R.raw.music),

    // Sounds
    Step(R.raw.music);

    // enum stuff
    private int resId;
    Sound(int resId) {
        this.resId = resId;
    }
    public int getRes() {
        return this.resId;
    }

}
