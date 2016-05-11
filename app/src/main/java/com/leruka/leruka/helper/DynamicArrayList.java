package com.leruka.leruka.helper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by leifb on 10.05.16.
 */
public class DynamicArrayList<E> extends ArrayList<E> {

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new DynamicIterator<>(this);
    }

}
