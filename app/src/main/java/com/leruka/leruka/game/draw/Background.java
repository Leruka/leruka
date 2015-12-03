package com.leruka.leruka.game.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.leruka.leruka.main.Central;

/**
 * This class holds a background object for the game. The background moves slowly while the player
 * is running.
 */
public class Background {

    /** The image that gets drawn. This is not the exact one gotten from the constructor */
    private Bitmap image;
    /** The current position of the background image. it goes from 0 to minPos (moves left) */
    private float position;
    /** The position of the image where it should jump back to 0 */
    private int minPos;
    /** The pixel width the background is moving each tick */
    private static float step = 1;

    /**
     * Creates a new Background object. The image should look nice when repeating. It has not to be
     * as wide as the screen, This class will take care of that kind of problems.
     * @param image The Bitmap used as the background image
     */
    public Background(Bitmap image) {
        this.position = 0;
        this.setUpLongImage(image, Central.getDisplayWidth(), Central.getDisplayHeight());
    }

    /**
     * Moves the background one step or to 0, if needed.
     */
    public void update() {
        this.position -= step;
        if (position <= minPos) position = 0;
    }

    /**
     * Draws the background to the canvas at the correct position.
     * @param canvas The canvas to draw on
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, position, 0, null);
    }

    /**
     * Creates a image long enough to fit two times into the screen and sets it as the image to draw.
     * @param image The image that should be the new background image
     * @param screenWidth the width of your screen (in pixels)
     * @param screenHeight the height of your screen (in pixels)
     */
    private void setUpLongImage(Bitmap image, int screenWidth, int screenHeight) {

        // get image width for performance
        int imageWidth = image.getWidth();

        // first: make high enough
        if (image.getHeight() < screenHeight) {
            int imageHeight = image.getHeight();
            // Create new image
            Bitmap img = Bitmap.createBitmap(imageWidth, screenHeight, Bitmap.Config.ARGB_8888);
            Canvas imgCanvas = new Canvas(img);
            // Get how often to copy
            int amountToCopy = (int) Math.ceil(((double)screenHeight) / ((double)imageHeight));
            // Copy
            for (int i = 0; i < amountToCopy; i++) {
                imgCanvas.drawBitmap(image, 0, i * imageHeight, null);
            }
            // set the image
            image = img;
        }


        // second: make long enough
        Bitmap large;
        // How long has the image to be
        int largeLength = screenWidth + imageWidth;
        // How often will the original image have to be copied
        int amountToCopy = (int) Math.ceil(((double)largeLength) / ((double)imageWidth));
        // Create larger image
        large = Bitmap.createBitmap(largeLength, screenHeight,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(large);
        // Copy the original
        for (int i = 0; i < amountToCopy; i++) {
            canvas.drawBitmap(image, i * imageWidth, 0, null);
        }
        // set the image
        this.image = large;
        // set minPos for repeat: possible after 1 image width
        this.minPos = -imageWidth;
    }


}
