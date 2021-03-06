package edu.prog3.Model;

/**
 *
 * Pratik Gayee pgayee
 * Jonathan Hale jonathanhale
 * Stephanie Akapkoun sakpakoun
 *
 * represents a block of memory, with address and size
 */
public class Block {

    private int size;
    private int address;

    public Block(int newSize, int newAddress) {
        this.size = newSize;
        this.address = newAddress;
    }

    /**
     * finds the buddy of this block, as per assignment requirements
     * @return the address of the buddy
     */
    public int findBuddyAddress() {
        if((this.address/this.size % 2) == 0) {
            return address+size;
        }
        else {
            return address-size;
        }
    }

    public int getSize() {
        return this.size;
    }

    public int getAddress() {
        return this.address;
    }

}
