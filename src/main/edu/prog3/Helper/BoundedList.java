package edu.prog3.Helper;

import java.util.ArrayList;

/**
 * essentially, this is a list that restricts the possible indexes between bounds
 * Should override all access/add/removal methods from ArrayList
 *
 * Expected Use: In this project, each index i represents the size of the blocks in the list element.
 *  So, the list of blocks with size 2^i is located at index i in this list
 *  there is a min and max block size, and sizes are in increments of 2^n
 */
public class BoundedList<T> extends ArrayList<T> {

    //Optional TODO: This could really be improved for better space efficiency, or just plain re-written to be less jank

    private int minIndex;
    private int maxIndex;

    public BoundedList(int minIdx, int maxIdx) {
        super(maxIdx); //capacity of this list, not necessarily the minimum amount of accessible elements
        this.minIndex = minIdx;
        this.maxIndex = maxIdx;

        //initialize elements up to maximum index with null
        //this makes the size consistent with the capacity
        for(int i=0; i<=maxIdx; i++) {
            super.add(null);
        }
    }

    public T get(int index) {
        if(index < minIndex || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Tried to get out of min and max bounds");
        } else {
            return super.get(index);
        }
    }

    @Override
    @Deprecated
    public boolean add(T element) {
        throw new UnsupportedOperationException("Cannot add to a Bounded List, use set instead");
    }

    @Override
    @Deprecated
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Cannot add to a Bounded List, use set instead");
    }

    public T set(int index, T element) {
        if(index < minIndex || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Tried to set index out of min and max bounds");
        } else {
            return super.set(index, element);
        }
    }

    @Override
    @Deprecated
    public T remove(int index) {
        throw new UnsupportedOperationException("Remove not supported for Bounded List");
    }

    @Override
    @Deprecated
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Remove not supported for Bounded List");
    }

    public int getMinIndex() {
        return this.minIndex;
    }

    public int getMaxIndex() {
        return this.maxIndex;
    }
}
