package com.leruka.leruka.helper;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by leifb on 10.05.16.
 */
public class DynamicIterator<E> implements Iterator<E> {

    private List<E> list;
    private int indexNextElement;
    private E currentElement;
    private boolean currentElementValid;

    public DynamicIterator(List<E> list) {
        this.list = list;
        this.indexNextElement = 0;
        this.currentElementValid = false;
    }

    @Override
    public boolean hasNext() {
        if (this.indexNextElement < this.list.size()) {
            this.currentElement = list.get(this.indexNextElement);
            this.currentElementValid = true;
            return true;
        }
        return false;
    }

    @Override
    public E next() {
        if (this.currentElementValid) {
            this.indexNextElement++;
            return this.copyAndDelete();
        }
        else {
            if (this.hasNext()) {
                this.indexNextElement++;
                return this.copyAndDelete();
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    private E copyAndDelete() {
        E element = this.currentElement;
        this.currentElement = null;
        return element;
    }
}
