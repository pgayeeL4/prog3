package edu.prog3;

import edu.prog3.Model.*;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * represents the whole memory system
 * will have the list of blocks
 * handles adding, removing, allocation, de-allocation, etc
 */
public class BuddySystemMemory {

    //TODO make this work

    //represents our "memory" of free Blocks
    //key is size, values are the list of blocks
    private HashMap<Integer, LinkedList<Block>> freeBlocks = new HashMap<>();

    //queue of deferred requests
    LinkedList<AllocationRequest> deferredRequests = new LinkedList<>();

    //set of existing allocations
    HashMap<Integer, Allocation> existingAllocations = new HashMap<>();

    public BuddySystemMemory(int minBlockSize, int maxBlockSize) {
        LinkedList<Block> temp = new LinkedList<>();
        temp.add(new Block(maxBlockSize, 0));
        this.freeBlocks.put(maxBlockSize, temp);
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
    public static int findList(int size) {
        return (int)(Math.log(size)/Math.log(2));
    }

    public static int nextPowerTwo(int x) {
        int k = x;

        //If not a power of 2, round up to the nearest power of 2
        if(!((k > 0) && ((k & (k - 1)) == 0))) {
            k = (int)Math.ceil(Math.log(k)/Math.log(2));
        }
        return (int)Math.pow(2, k);
    }

}
