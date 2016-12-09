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
        initLists(minBlockSize, maxBlockSize);
    }

    public boolean allocate(AllocationRequest request, boolean deferred) {
        //TODO implement allocation
        
        return false;
    }

    public void checkDeferredList(){
        for(AllocationRequest defRequest : deferredRequests) {
            if (allocate(defRequest, true)) {
                //check deferred list
                deferredRequests.remove(defRequest);
                System.out.println("Deferred Request with ID " + defRequest.getId() + " allocated; addr = " +
                                    existingAllocations.get(defRequest.getId())
                                                        .getBlock()
                                                        .getAddress());
            }
        }
    }

    public void deallocate(DeallocationRequest request) {
        Allocation allocatedMem = existingAllocations.get(request.getId()); //getting allocation
        Block allocBlock = allocatedMem.getBlock(); // get block
        existingAllocations.remove(allocatedMem);//remove allocated memory from allocatedList

        deallocateBuddy(allocBlock);

        System.out.println("Deallocated Allocation with ID: " + request.getId());
        System.out.println("Deallocated Block at address: " + allocBlock.getAddress() +
                "and size: " + allocBlock.getSize());

        checkDeferredList();
    }

    /**
     * If buddy is found, recursively merge blocks
     * Else
     *
     */
    public void deallocateBuddy(Block allocBlock){
        int buddyAddress = allocBlock.findBuddyAddress();
        int listID = allocBlock.getSize(); //get list ID for list that has the same size blocks as allocated memory

        LinkedList<Block> list = freeBlocks.get(listID);
        for(Block freeBlock : list){
            // check freeBlock list
            if(freeBlock.getAddress() == buddyAddress){ //if buddy address is the same as free block
                Block mergedBlock = new Block(allocBlock.getSize()*2,
                        ((allocBlock.getAddress() < buddyAddress)?allocBlock.getAddress():buddyAddress) );
                list.remove(freeBlock);
                deallocateBuddy(mergedBlock);
                break;
            }
            else{ //not found
                Block buddyBlock = new Block(allocBlock.getSize(), buddyAddress); //add entry for allocBlock buddy
                list.add(buddyBlock);
                break;
            }
        }
        return;
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

    /**
     *
     * @param min
     * @param max
     */
    private void initLists(int min, int max) {
        int startPow = findList(min);
        int endPow = findList(max);

        for(int i = startPow; i <= endPow; i++) {
            System.out.println("current is: " + Math.pow(2, i));
            this.freeBlocks.put((int)Math.pow(2, i), new LinkedList<Block>());
        }
        this.freeBlocks.get(max).add(new Block(max, 0));
    }

}
