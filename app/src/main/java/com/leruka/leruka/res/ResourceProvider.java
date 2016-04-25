package com.leruka.leruka.res;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.leruka.leruka.R;
import com.leruka.leruka.game.draw.Animation;
import com.leruka.leruka.main.Central;

/**
 * Created by leifb on 04.12.15.
 */
public class ResourceProvider {

    private static BitmapFactory.Options dimensionConfig;

    static {
        dimensionConfig = new BitmapFactory.Options();
        dimensionConfig.inJustDecodeBounds = true;
    }

    public static Bitmap loadImageByHeight(int id, int targetHeight) {

        // load height of the image
        Point source =  ResourceProvider.loadDimensions(id);

        // get sample size: save memory by not loading the full image and than scaling down
        int sampleSize = getSampleSize(source.y, targetHeight);

        // load the real image
        Bitmap img = ResourceProvider.loadSampledBitmap(id, sampleSize);

        // scale the image
        return ResourceProvider.scaleBitmapToHeight(img, targetHeight);
    }

    public static Bitmap loadImageByWidth(int id, int targetWidth) {

        // load height of the image
        Point source =  ResourceProvider.loadDimensions(id);

        // get sample size: save memory by not loading the full image and than scaling down
        int sampleSize = getSampleSize(source.x, targetWidth);

        // load the real image
        Bitmap img = ResourceProvider.loadSampledBitmap(id, sampleSize);

        // scale the image
        return ResourceProvider.scaleBitmapToWidth(img, targetWidth);
    }

    public static Animation loadAnimation(Iterable<Integer> ids) {
        return null;
    }

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
        if (bitmap.getHeight() != targetHeight) {
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
        if (bitmap.getWidth() != targetWidth) {
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
