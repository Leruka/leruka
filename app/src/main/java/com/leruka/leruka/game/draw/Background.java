package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.leruka.leruka.helper.Message;

/**
 * Created by leif on 09.11.15.
 */
public class Background {

    // Attributes
    private Bitmap image;
    private float position;
    private int width;

    private static float step = 1;

    // Constructor
    public Background(Bitmap image, int screenWidth) {
        this.position = 0;
        this.setUpLongImage(image, screenWidth);
    }

    // Methods
    public void update() {
        this.position -= step;
        if (position < width) position = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, position, 0, null);
    }

    /**
     * Creates a image long enough to fit two times into the screen and sets it as the image
     * @param image The image that should be the new background image
     * @param screenWidth the width of your screen (in pixels)
     */
    private void setUpLongImage(Bitmap image, int screenWidth) {
        Message.showMessage("screen width: " + screenWidth);
        // the amount the images has to be displayed aside
        int amount = 1;
        // get image width for performance
        int imageWidth = image.getWidth();
        // Create bitmap for new background
        Bitmap large = image.copy(Bitmap.Config.ARGB_8888, true);
        // Create canvas
        Canvas c = new Canvas(large);
        // double until long enough
        while (large.getWidth() / 2 < screenWidth) {
            amount++;
            large = Bitmap.createBitmap(imageWidth * amount, image.getHeight(), Bitmap.Config.ARGB_8888);
            c.setBitmap(large);
            for (int i = 0; i < amount; i++) {
                c.drawBitmap(image, imageWidth * i, 0, null);
            }
        }
        // set the image
        this.image = large;
        this.width = -screenWidth;

        Message.showMessage("created image width " + large.getWidth());
    }


}
