package edu.prog3.Model;

/**
 * represents a current allocation, with id, size, and address
 *
 * This is an allocation that has happened, it is NOT an allocation request
 */
public class Allocation {

    private int id;
    private Block block;

    public Allocation(int newId, int newSize, int newAddress) {
        this.id = newId;
        this.block = new Block(newSize, newAddress);
    }

    public Allocation(int newId, Block newBlock) {
        this.id = newId;
        this.block = newBlock;
    }

    public int getId() {
        return this.id;
    }

    public Block getBlock() {
        return this.block;
    }

}
