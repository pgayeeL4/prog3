package edu.prog3.Model;

/**
 * Created by Pratik on 12/1/2016
 * represents a current allocation, with id, size, and address
 */
public class Allocation {

    private int id;
    private int size;
    private int address;

    public Allocation(int newId, int newSize, int newAddress) {
        this.id = newId;
        this.size = newSize;
        this.address = newAddress;
    }

    public int getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

}
