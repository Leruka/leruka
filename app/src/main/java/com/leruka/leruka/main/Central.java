package com.leruka.leruka.main;

import android.app.Activity;

/**
 * Created by leif on 11.11.15.
 */
public class Central {

    private static Activity currentActivity;


    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        Central.currentActivity = currentActivity;
    }
}
