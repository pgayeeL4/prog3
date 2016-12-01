package edu.prog3.Helper;

import java.util.ArrayList;

/**
 * Created by Pratik on 12/1/2016
 * essentially, this is a list that restricts the possible indexes between bounds
 * Should override all access/add/removal methods from ArrayList
 *
 * Expected Use: In this project, each index i represents the size of the blocks in the list element.
 *  So, the list of blocks with size 2^i is located at index i in this list
 *  there is a min and max block size, and sizes are in increments of 2^n
 */
public class MinMaxList<T> extends ArrayList<T> {

    //Optional TODO: This could really be improved for better space efficiency, or just plain re-written to be less jank

    private int minIndex;
    private int maxIndex;

    public MinMaxList(int min, int max) {
        super(max); //capacity of this list, not necessarily the minimum amount of accessible elements
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
            throw new ArrayIndexOutOfBoundsException("Tried to set index out of min and max bounds");
        } else {
            return super.set(index, element);
        }
    }

    public T remove(int index) {
        if(index < minIndex || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Tried to remove at index out of min and max bounds");
        } else {
            return super.remove(index);
        }
    }

    public int getMinIndex() {
        return this.minIndex;
    }

    public int getMaxIndex() {
        return this.maxIndex;
    }
}
