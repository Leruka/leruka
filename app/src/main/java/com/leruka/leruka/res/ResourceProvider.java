package com.leruka.leruka.res;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.annotation.Nullable;

import com.leruka.leruka.game.Hitbox;
import com.leruka.leruka.game.draw.Animation;
import com.leruka.leruka.game.draw.Drawable;
import com.leruka.leruka.game.draw.Image;
import com.leruka.leruka.game.track.Entity;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.main.Central;

/**
 * Created by leifb on 04.12.15.
 */
public class ResourceProvider {

    private static BitmapFactory.Options dimensionConfig;
    private static ResourceCache cache;

    static {
        dimensionConfig = new BitmapFactory.Options();
        dimensionConfig.inJustDecodeBounds = true;
        cache = new ResourceCache();
    }

    // Public

    public static Bitmap loadImageByHeight(int id, int targetHeight) {

        // Check for cache
        ResourceDescription desc = new ResourceDescription(id, targetHeight);
        if (cache.hasImageByHeight(desc))
            return cache.getImageByHeight(desc);

        // load size of the image
        Point source =  ResourceProvider.loadDimensions(id);

        // get sample size: save memory by not loading the full image and than scaling down
        int sampleSize = getSampleSize(source.y, targetHeight);

        // load the real image
        Bitmap img = ResourceProvider.loadSampledBitmap(id, sampleSize);

        // scale the image
        img = ResourceProvider.scaleBitmapToHeight(img, targetHeight);

        // Cache it
        cache.addImageByHeight(desc, img);

        // Return it
        return img;
    }

    public static Bitmap loadImageByWidth(int id, int targetWidth) {

        // Check for cache
        ResourceDescription desc = new ResourceDescription(id, targetWidth);
        if (cache.hasImageByWidth(desc))
            return cache.getImageByWidth(desc);

        // load size of the image
        Point source =  ResourceProvider.loadDimensions(id);

        // get sample size: save memory by not loading the full image and than scaling down
        int sampleSize = getSampleSize(source.x, targetWidth);

        // load the real image
        Bitmap img = ResourceProvider.loadSampledBitmap(id, sampleSize);

        // scale the image
        img = ResourceProvider.scaleBitmapToWidth(img, targetWidth);

        // Cache it
        cache.addImageByWidth(desc, img);

        // Return it
        return img;
    }

    public static Hitbox loadHitbox(int heightId, int ratioId, @Nullable Point position) {
        // Null guard
        if (position == null)
            position = new Point(0,0);

        // Get height and width
        int height = Measure.ph(Central.getResources().getInteger(heightId));
        int width = (int) (height * Central.getResources().getInteger(ratioId) / 100.);

        // Create hitbox
        return new Hitbox(position.x, position.y, width, height);
    }

    public static Animation loadAnimation(int imageArrayID, int repeatsArrayID, int targetHeight) {

        // Get the array of image ids
        TypedArray arr = Central.getResources().obtainTypedArray(imageArrayID);
        int len = arr.length();
        Bitmap[] frames = new Bitmap[len];

        // Get the repeats array
        int[] repeats = Central.getResources().getIntArray(repeatsArrayID);

        // load each image
        for (int i = 0; i < len; i++) {
            frames[i] = loadImageByHeight(arr.getResourceId(i,0), targetHeight);
        }

        // Create the animation, clean up and return
        Animation animation = new Animation(frames, repeats);
        arr.recycle();
        return animation;
    }

    public static int loadInt(int resId) {
        return Central.getResources().getInteger(resId);
    }

    public static Point loadImageShift(int shiftX, int shiftY, Point imageSize) {
        return new Point(
                (int) (ResourceProvider.loadInt(shiftX) / 100.0 * imageSize.x),
                (int) (ResourceProvider.loadInt(shiftY) / 100.0 * imageSize.y)
        );
    }

    public static Entity loadObstacle(
            int imageId,
            int imageHeight,
            int imageShiftX,
            int imageShiftY,
            int boxHeight,
            int boxRatio
    ) {
        // Load Hitbox
        Hitbox hitbox = ResourceProvider.loadHitbox(boxHeight, boxRatio, null);

        // Load image
        Drawable image = new Image(ResourceProvider.loadImageByHeight(
                imageId,
                Measure.phr(imageHeight)));

        // Load image shift
        Point imageShift = ResourceProvider.loadImageShift(imageShiftX, imageShiftY, image.getSize());

        return new Entity(hitbox, image, imageShift);
    }

    // Private

    private static Point loadDimensions(int id) {
        BitmapFactory.decodeResource(Central.getResources(), id, dimensionConfig);
        return new Point(dimensionConfig.outWidth, dimensionConfig.outHeight);
    }

    private static int getSampleSize(int sourceSize, int targetSize) {
        // Calculate the largest value that is power of 2 and keeps the size larger than requested.
        int sampleSize = 1;
        if (sourceSize > targetSize) {
            final int halfSize = sourceSize / 2;
            while ((halfSize / sampleSize) > targetSize)
                sampleSize *= 2;
        }
        return sampleSize;
    }

    private static Bitmap scaleBitmapToHeight(Bitmap bitmap, double targetHeight) {
        if (bitmap.getHeight() != (int) targetHeight) {
            return Bitmap.createScaledBitmap(
                    bitmap,
                    (int) (((double)bitmap.getWidth()) / ((double)bitmap.getHeight())
                            * (targetHeight)),
                    (int) targetHeight,
                    true);
        }
        return bitmap;
    }

    private static Bitmap scaleBitmapToWidth(Bitmap bitmap, double targetWidth) {
        if (bitmap.getWidth() != ((int) targetWidth)) {
            return Bitmap.createScaledBitmap(
                    bitmap,
                    (int) targetWidth,
                    (int) (((double)bitmap.getHeight()) / ((double)bitmap.getWidth())
                            * (targetWidth)),
                    true);
        }
        return bitmap;
    }

    private static Bitmap loadSampledBitmap(int id, int sampleSize) {
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inSampleSize = sampleSize;
        Bitmap img = BitmapFactory.decodeResource(Central.getResources(), id, ops)
                .copy(Bitmap.Config.ARGB_8888, true);
        img.setDensity(Bitmap.DENSITY_NONE);
        return img;
    }



}
