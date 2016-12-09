package edu.prog3;

import edu.prog3.Model.Allocation;
import edu.prog3.Model.AllocationRequest;
import edu.prog3.Model.Block;
import edu.prog3.Model.DeallocationRequest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Pratik Gayee pgayee
 * Jonathan Hale jonathanhale
 * Stephanie Akapkoun sakpakoun
 *
 * represents the whole memory system
 * will have the list of blocks
 * handles adding, removing, allocation, de-allocation, etc
 */
public class BuddySystemMemory {

    //TODO make this work

    static int maxSize = 0;
    static int minSize = 0;
    int origId = -1;

    //represents our "memory" of free Blocks
    //key is size, values are the list of blocks
    private HashMap<Integer, LinkedList<Block>> freeBlocks = new HashMap<>();

    //queue of deferred requests
    ConcurrentLinkedQueue<AllocationRequest> deferredRequests = new ConcurrentLinkedQueue<>();

    //set of existing allocations key:ID of allocation
    HashMap<Integer, Allocation> existingAllocations = new HashMap<>();

    public BuddySystemMemory(int minBlockSize, int maxBlockSize) {
        this.maxSize = maxBlockSize;
        this.minSize = minBlockSize;
        initLists(minBlockSize, maxBlockSize);
    }

    public boolean allocate(AllocationRequest request, boolean deferred) {
        //TODO implement allocation
        origId = request.getId();

        int goodSize = nextPowerTwo(request.getSize());
        LinkedList<Block> list = freeBlocks.get(goodSize);

        if (list.size() > 0) {
            //get the index of block with smallest address
            int addr = list.get(0).getAddress();
            int idx1 = 0;
            for (int idx = 0; idx < list.size(); idx++) {
                if (list.get(idx).getAddress() < addr) {
                    addr = list.get(idx).getAddress();
                    idx1 = idx;
                }
            }
            //allocate and remove block from free list
            Allocation alloc = new Allocation(request.getId(), list.remove(idx1));
            existingAllocations.put(alloc.getId(), alloc);
//            System.out.println("Allocated, current number of allocations is: " + existingAllocations.size());
//            System.out.println("Allocation pulled from list: " + goodSize);

            if(deferred) {
                System.out.println("Deferred request " + request.getId() + " allocated; addr = " +
                        String.format("0x%08X", existingAllocations.get(request.getId())
                                .getBlock()
                                .getAddress()));
            } else {
                System.out.println("Success; addr = " + String.format("0x%08X", alloc.getBlock().getAddress()));
            }
            return true;
        } else {
            //look in next larger list to find block
            //larger block will then be split until original size is reached
            //all split off blocks are added to appropriate lists
            //correct block(after splits) is used to satisfy request
            // if(allocateBuddy(new AllocationRequest(request.getId(), goodSize*2))) {
            int foundListIdx = allocateBuddy(new AllocationRequest(request.getId(), goodSize * 2));
            if (foundListIdx != -1) {
                list = freeBlocks.get(foundListIdx);
                int minAddr = findMin(list);
                splitAllocate(list.get(minAddr), goodSize, deferred);
                return true;
            } else {
                if(!deferred){
                    deferredRequests.add(request);
                    System.out.println("Request Deferred");
                }
                return false;
            }
        }
    }




    public int allocateBuddy(AllocationRequest request) {
        int goodSize = nextPowerTwo(request.getSize());
        LinkedList<Block> list = freeBlocks.get(goodSize);
        if (list == null) {
            return -1;
        } else {
            if (list.size() > 0) {
                return goodSize;
            } else {
                return allocateBuddy(new AllocationRequest(request.getId(), goodSize * 2));
            }
        }
    }

    public void splitAllocate(Block block, int size, boolean deferred){

        while(block.getSize() != size){
            LinkedList<Block> list = freeBlocks.get(block.getSize());
            Block savedBlock = block;
            list.remove(block);
            Block newBlock1  = new Block(savedBlock.getSize()/2, savedBlock.getAddress());
            Block newBlock2  = new Block(savedBlock.getSize()/2, savedBlock.getAddress()+savedBlock.getSize()/2);
            freeBlocks.get(newBlock2.getSize()).add(newBlock2);
            block = newBlock1;
        }
        freeBlocks.get(block.getSize()).add(block); //Add block to correct free blocks list
        allocate(new AllocationRequest(origId, size), deferred);
        }

    public int findMin(LinkedList<Block> list){
        int addr = list.get(0).getAddress();
        int idx1 = 0;
        for(int idx = 0; idx < list.size(); idx++) {
            if(list.get(idx).getAddress() < addr) {
                addr = list.get(idx).getAddress();
                idx1 = idx;
            }
        }
    return idx1;
    }

    public void checkDeferredList(){
        //LinkedList<AllocationRequest> tempList = deferredRequests;
        for(AllocationRequest defRequest : deferredRequests) {
            if (allocate(defRequest, true)) {
                //check deferred list
                deferredRequests.remove(defRequest);
            }
        }
    }

    public void deallocate(DeallocationRequest request) {
        Allocation allocatedMem = existingAllocations.get(request.getId()); //getting allocation
        Block allocBlock = allocatedMem.getBlock(); // get block
        //existingAllocations.remove(allocatedMem);//remove allocated memory from allocatedList
        existingAllocations.remove(allocatedMem.getId());

        deallocateBuddy(allocBlock);

        System.out.println("Success.");
        //System.out.println("Success Deallocated Allocation with ID: " + request.getId());
//        System.out.println("Deallocated Block at address: " + Integer.toHexString(allocBlock.getAddress()) +
//                " and size: " + allocBlock.getSize());

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
        if(list.isEmpty()) {
            list.add(allocBlock);
        }
        else {
            for (Block freeBlock : list) {
                // check freeBlock list
                if (freeBlock.getAddress() == buddyAddress) { //if buddy address is the same as free block
                    Block mergedBlock = new Block(allocBlock.getSize() * 2,
                            ((allocBlock.getAddress() < buddyAddress) ? allocBlock.getAddress() : buddyAddress));
                    list.remove(freeBlock);
                    deallocateBuddy(mergedBlock);
                    break;
                } else { //not found
                    Block buddyBlock = new Block(allocBlock.getSize(), buddyAddress); //add entry for allocBlock buddy
                    list.add(allocBlock);
                    freeBlocks.put(allocBlock.getSize(), list);
                    break;
                }
            }
        }
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
        int z = 0;
        //If not a power of 2, round up to the nearest power of 2
        if(!((k > 0) && ((k & (k - 1)) == 0))) {
            k = (int)Math.ceil(Math.log(k)/Math.log(2));
            z =  (int)Math.pow(2, k);
        } else {
            z = k;
        }

        if(z < minSize) {
            z = minSize;
        }

        return z;
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
            //System.out.println("current is: " + Math.pow(2, i));
            this.freeBlocks.put((int)Math.pow(2, i), new LinkedList<Block>());
        }
        this.freeBlocks.get(max).add(new Block(max, 0));
    }

}
