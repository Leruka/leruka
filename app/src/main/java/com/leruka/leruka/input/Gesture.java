package com.leruka.leruka.input;

import android.view.MotionEvent;

import com.leruka.leruka.helper.Message;

/**
 * Created by leif on 09.11.15.
 */
public class Gesture {

    public static void processGesture(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // msg.showMessage("" + e1.getY() + "..... " + e2.getY());

        if (e1.getX() - e2.getY() < 0 ) {
            Message.showMessage("runter");

        } else
        if (e2.getX() - e1.getY() < 0) {
            Message.showMessage("hoch" );
        }

    }

    private static void detect() {
        //TODO
    }


}
