package edu.prog3;

import edu.prog3.Helper.BoundedList;
import edu.prog3.Model.*;

import java.util.HashSet;
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

    //TODO make this work

    //represents our "memory" of free Blocks
    private BoundedList<LinkedList<Block>> freeBlocks;

    //queue of deferred requests
    LinkedList<AllocationRequest> deferredRequests = new LinkedList<>();

    //set of existing allocations
    HashSet<Allocation> existingAllocations = new HashSet<>();

    //initializes the initial list of block lists and the starting free block
    public BuddySystemMemory(int minBlockSize, int maxBlockSize) {
        this.freeBlocks = new BoundedList<>(getIndex(minBlockSize), getIndex(maxBlockSize));
        this.freeBlocks.get(getIndex(maxBlockSize)).add(new Block(maxBlockSize, 0));
    }

    public void allocate(AllocationRequest request) {
        //TODO implement allocation
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void deallocate(DeallocationRequest request) {
        //TODO implement deallocation
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * takes in a size(of power 2) and gives back the appropriate index for the block's list
     * @param size size of the block
     * @return index of the block's list
     */
    public static int getIndex(int size) {
        return (int)(Math.log(size)/Math.log(2));
    }

}
