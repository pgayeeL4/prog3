package edu.prog3;

import edu.prog3.Helper.MinMaxList;
import edu.prog3.Model.Block;

import java.util.LinkedList;

/**
 * represents the whole memory system
 * will have the list of blocks
 * handles adding, removing, allocation, de-allocation, etc
 *
 * Expected Use: In this project, each index i represents the size of the blocks in the list element.
 *  So, the list of blocks with size 2^i is located at index i in this list
 *  there is a min and max block size, and sizes are in increments of 2^n
 */
public class BuddySystemMemory {

    MinMaxList<LinkedList<Block>> memory;

    public BuddySystemMemory(int minBlockSize, int maxBlockSize) {
        this.memory = new MinMaxList<>(minBlockSize, maxBlockSize);
    }

}
