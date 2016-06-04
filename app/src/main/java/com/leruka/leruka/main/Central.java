package com.leruka.leruka.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;

import com.leruka.leruka.activity.GuestMainActivity;
import com.leruka.leruka.activity.UserMainActivity;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.sound.Effects;
import com.leruka.leruka.user.LUser;

/**
 * This Class is a central place to temporary store data and settings.
 */
public class Central {

    // ATTRIBUTES

    /** Determines, if the app should run for developers: This will enable debug messages */
    public static final boolean isDev = true;
    public static final boolean drawRects = false;
    /** The time that should be between each tick. Adjusting later on may cause problems! */
    public static final int targetTickNanos = (int) 1e+7; // 1/100 s

    public static final String LOG_TAG_MAIN = "leruka";


    private static final double groundLevelPercentage = 5.0;


    /** A resources object from an activity: This can be used to load bitmaps or other resources */
    private static Resources resources;
    /** The current active activity */
    private static Activity currentActivity;
    /** The width of the device screen. NOT always the width of the available space */
    private static int displayWidth;
    /** The height of the device screen. NOT always the height of the available space */
    private static int displayHeight;
    /** The amount of pixels that an obstacle should move when it gets updated */
    private static int obstacleSpeed;


    public static void goToMain(Context context) {
        Intent intent = new Intent(context, LUser.isLoggedIn() ? UserMainActivity.class : GuestMainActivity.class);
        context.startActivity(intent);
    }

    // GETTER

    public static int getGroundLevel() {
        return Measure.ph(Central.groundLevelPercentage);
    }

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

    public static int getObstacleSpeed() {
        return obstacleSpeed;
    }

    public static Effects createEffects() {
        return new Effects(Central.getCurrentActivity());
    }

    // SETTER

    @SuppressWarnings("SuspiciousNameCombination")
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

    public static void setObstacleSpeed(int obstacleSpeed) {
        Central.obstacleSpeed = obstacleSpeed;
    }

}
