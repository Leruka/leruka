package com.leruka.leruka.input;

import android.view.MotionEvent;

import com.leruka.leruka.helper.Message;

/**
 * Created by ruth on 09.11.15.
 */
public class Gesture {

    private static final int NONE = 0;
    private static final int UP = 1;
    private static final int DOWN = 2;

    public static void processGesture(MotionEvent e1, MotionEvent e2) {
        int gesture = detect(e1, e2);
        switch (gesture) {
            case UP:
                Message.showMessage("hoch");
                break;
            case DOWN:
                Message.showMessage("runter");
                break;
        }
    }

    private static int detect(MotionEvent e1, MotionEvent e2) {
        if (e1.getY() - e2.getY() < -50 ) return DOWN;
        if (e1.getY() - e2.getY() > 50) return UP;
        return NONE;
    }


}
