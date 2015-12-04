package com.leruka.leruka.main;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * This Class is a central place to temporary store data and settings.
 */
public class Central {

    //region ATTRIBUTES

    /** Determines, if the app should run for developers: This will enable debug messages */
    public static final boolean isDev = true;
    /** The time that should be between each tick. Adjusting later on may cause problems! */
    public static final int targetTickNanos = (int) 1e+7; // 1/10 s

    /** A resources object from an activity: This can be used to load bitmaps or other resources */
    private static Resources resources;
    /** The current active activity */
    private static Activity currentActivity;
    /** The width of the device screen. NOT always the width of the available space */
    private static int displayWidth;
    /** The height of the device screen. NOT always the height of the available space */
    private static int displayHeight;

    //endregion

    //region GETTER

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static Resources getResources() {
        return resources;
    }

    public static int getDisplayWidth() {
        return displayWidth;
    }

    public static int getDisplayHeight() {
        return displayHeight;
    }

    //endregion

    //region SETTER

    public static void setCurrentActivity(Activity currentActivity) {
        // On first time: get the display metrics
        if (Central.currentActivity == null) {
            Point size = new Point();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                currentActivity.getWindowManager().getDefaultDisplay().getRealSize(size);
            } else {
                DisplayMetrics metrics = new DisplayMetrics();
                currentActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                size.set(metrics.widthPixels, metrics.heightPixels);
            }
            // depending on device, x and y my be mixed. use larger as width for landscape
            if (size.x > size.y) {
                Central.displayHeight = size.y;
                Central.displayWidth = size.x;
            }
            else {
                Central.displayHeight = size.x;
                Central.displayWidth = size.y;
            }


        }
        // Set the activity
        Central.currentActivity = currentActivity;
    }

    public static void setResources(Resources resources) {
        Central.resources = resources;
    }

    //endregion

}
