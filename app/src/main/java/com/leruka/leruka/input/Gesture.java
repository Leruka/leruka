package com.leruka.leruka.input;

import android.view.MotionEvent;

import com.leruka.leruka.game.Game;

/**
 * Created by ruth on 09.11.15.
 */
public class Gesture {


    public static void processGesture(MotionEvent e1, MotionEvent e2) {
        Type gesture = detect(e1, e2);
        if (gesture != Type.NONE) Game.processGesture(gesture);
    }

    private static Type detect(MotionEvent e1, MotionEvent e2) {
        if (e1.getY() - e2.getY() < -50 ) return Type.DOWN;
        if (e1.getY() - e2.getY() > 50) return Type.UP;
        return Type.NONE;
    }

    public enum Type {
        NONE,
        UP,
        DOWN
    }


}
