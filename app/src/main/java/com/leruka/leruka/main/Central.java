package com.leruka.leruka.main;

import android.app.Activity;
import android.content.res.Resources;

/**
 * Created by leif on 11.11.15.
 */
public class Central {

    // Finals
    public static final boolean isDev = true;
    public static final int targetTickNanos = (int) 1e+7; // 1/10 s

    // Attributes
    private static Resources resources;
    public static Activity getCurrentActivity() {
        return currentActivity;
    }
    private static Activity currentActivity;

    public static void setCurrentActivity(Activity currentActivity) {
        Central.currentActivity = currentActivity;
    }

    public static Resources getResources() {
        return resources;
    }

    public static void setResources(Resources resources) {
        Central.resources = resources;
    }
}
