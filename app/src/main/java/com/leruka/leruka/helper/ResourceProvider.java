package com.leruka.leruka.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.leruka.leruka.R;
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

    public static Bitmap loadImageWithHeight(int id, int targetHeight) {
        // load height of the image
        BitmapFactory.decodeResource(Central.getResources(), id, dimensionConfig);
        int sourceHeight = dimensionConfig.outHeight;
        int sourceWidth = dimensionConfig.outWidth;

        // get sample size: save memory by not loading the full image and than scaling down
        int inSampleSize = 1;
        if (sourceHeight > targetHeight) {
            final int halfHeight = sourceHeight / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > targetHeight)
                inSampleSize *= 2;
        }

        // load the real image
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inSampleSize = inSampleSize;
        Bitmap img = BitmapFactory.decodeResource(Central.getResources(), id, ops)
                .copy(Bitmap.Config.ARGB_8888, true);

        img.setDensity(Bitmap.DENSITY_NONE);

        // scale the image
        if (img.getHeight() != targetHeight) {
            return Bitmap.createScaledBitmap(img, (int)(((double)sourceWidth) / ((double)sourceHeight) * ((double)targetHeight)),
                    targetHeight, true);
        }
        return img;
    }


}
