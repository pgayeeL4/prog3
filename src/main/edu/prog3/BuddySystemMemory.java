package edu.prog3;

import edu.prog3.Helper.BoundedList;
import edu.prog3.Model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * represents the whole memory system
 * will have the list of blocks
 * handles adding, removing, allocation, de-allocation, etc
 *
 * Expected Use for BoundedList: In this project, each index i represents the size of the blocks in the list element.
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
    HashMap<Integer, Allocation> existingAllocations = new HashMap<>();

    //initializes the initial list of block lists and the starting free block
    public BuddySystemMemory(int minBlockSize, int maxBlockSize) {
        this.freeBlocks = new BoundedList<>(findList(minBlockSize), findList(maxBlockSize));
        this.freeBlocks.get(findList(maxBlockSize)).add(new Block(maxBlockSize, 0));
    }

    public boolean allocate(AllocationRequest request) {
        //TODO implement allocation

        throw new UnsupportedOperationException("Not implemented yet");

    }

    public void checkDeferredList(){
        for(AllocationRequest defRequest : deferredRequests) {
            if (allocate(defRequest)) {//check deferred list
                deferredRequests.remove(defRequest);
            }
        }
    }

    public void deallocate(DeallocationRequest request) {
        Allocation allocatedMem = existingAllocations.get(request.getId()); //getting allocation
        Block allocBlock = allocatedMem.getBlock(); // get block
        existingAllocations.remove(allocatedMem);//remove allocated memory from allocatedList

        int buddyAddress = allocBlock.findBuddyAddress();
        int listID = findList(allocBlock.getSize()); //get list ID for list that has the same size blocks as allocated memory

        LinkedList<Block> list = freeBlocks.get(listID);
        for(Block freeBlock : list){ // check freeBlock list
            if(freeBlock.getAddress() == buddyAddress){ //if buddy address is the same as free block
                Block mergedBlock = new Block(allocBlock.getSize()*2, (allocBlock.getAddress()<buddyAddress?allocBlock.getAddress():buddyAddress) );
              list.remove(freeBlock);
                break;
            }
            else{ //not found
                Block bjuddyBlock = new Block(allocBlock.getSize(), buddyAddress); //add entry for allocBlock buddy
                list.add(buddyBlock);
            }
        }

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

}
