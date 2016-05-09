package com.leruka.leruka.helper;

import com.leruka.leruka.main.Central;

/**
 * Created by leifb on 04.12.15.
 */
public class Measure {

    /**
     * Returns the percent % pixel of the height of the screen.
     * @param percent the percentage of height you want
     * @return pixels in int
     */
    public static int ph(double percent) {
        return (int) (Central.getDisplayHeight() * (percent / 100d));
    }

    /**
     * Returns the percent % pixel of the width of the screen.
     * @param percent the percentage of width you want
     * @return pixels in int
     */
    public static int pw(double percent) {
        return (int) (Central.getDisplayWidth() * (percent / 100d));
    }
}
