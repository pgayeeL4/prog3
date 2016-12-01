package edu.prog3.Helper;

import java.util.ArrayList;

/**
 * Created by Pratik on 12/1/2016
 * essentially, this is a list that restricts the possible indexes between bounds
 *
 * Expected Use: In this project, each index i represents the size of the blocks in the list element.
 *  So, the list of blocks with size 2^i is located at index i in this list
 */
public class MinMaxList<T> extends ArrayList<T> {

    private int minIndex;
    private int maxIndex;

    public MinMaxList(int min, int max) {
        this.minIndex = min;
        this.maxIndex = max;
    }

    public T get(int index) {
        if(index < minIndex || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Tried to get out of min and max bounds");
        } else {
            return super.get(index);
        }
    }

    public boolean add(T element) {
        return super.add(element);
    }

    public void add(int index, T element) {
        if(index < minIndex || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Tried to add to index out of min and max bounds");
        } else {
            super.add(index, element);
        }
    }

    public T set(int index, T element) {
        if(index < minIndex || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Tried to add to index out of min and max bounds");
        } else {
            return super.set(index, element);
        }
    }

    public int getMinIndex() {
        return this.minIndex;
    }

    public int getMaxIndex() {
        return this.maxIndex;
    }
}
