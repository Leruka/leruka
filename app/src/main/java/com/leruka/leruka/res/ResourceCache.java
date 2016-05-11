package com.leruka.leruka.res;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leifb on 11.05.16.
 */
class ResourceCache {

    private Map<ResourceDescription, Bitmap> imagesByHeight;
    private Map<ResourceDescription, Bitmap> imagesByWidth;

    ResourceCache() {
        this.imagesByHeight = new HashMap<>();
        this.imagesByWidth = new HashMap<>();
    }

    void addImageByHeight(ResourceDescription imageDescription, Bitmap image) {
        this.imagesByHeight.put(imageDescription, image);
    }

    void addImageByWidth(ResourceDescription imageDescription, Bitmap image) {
        this.imagesByWidth.put(imageDescription, image);
    }

    Bitmap getImageByHeight(ResourceDescription imageDescription) {
        return this.imagesByHeight.get(imageDescription);
    }

    Bitmap getImageByWidth(ResourceDescription imageDescription) {
        return this.imagesByWidth.get(imageDescription);
    }

    boolean hasImageByHeight(ResourceDescription imageDescription) {
        return this.imagesByHeight.containsKey(imageDescription);
    }

    boolean hasImageByWidth(ResourceDescription imageDescription) {
        return this.imagesByWidth.containsKey(imageDescription);
    }
}
