package com.leruka.leruka.main;

import android.app.Activity;

/**
 * Created by leif on 11.11.15.
 */
public class Central {

    // Finals
    public static final boolean isDev = true;

    // Attributes
    public static Activity getCurrentActivity() {
        return currentActivity;
    }
    private static Activity currentActivity;

    public static void setCurrentActivity(Activity currentActivity) {
        Central.currentActivity = currentActivity;
    }
}
