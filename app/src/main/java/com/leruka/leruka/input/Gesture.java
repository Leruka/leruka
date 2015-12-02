package com.leruka.leruka.input;

import android.view.MotionEvent;

import com.leruka.leruka.helper.Message;

/**
 * Created by leif on 09.11.15.
 */
public class Gesture {

    public static void processGesture(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getY() - e2.getY() < -50 ) {
            Message.showMessage("runter d " + Math.round(Math.abs(e1.getY() - e2.getY())));

        }
        else if (e1.getY() - e2.getY() > 50) {
            Message.showMessage("hoch d " + Math.round(e1.getY() - e2.getY()));
        }

    }

    private static void detect() {
        //TODO
    }


}
