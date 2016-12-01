package edu.prog3.Model;

/**
 * denotes a request for allocation, extends Request for ID, but also includes size
 */
public class AllocationRequest extends Request {

    public AllocationRequest(int newId, int newSize) {
        super(newId);
        this.size = newSize;
    }

    private int size;

    public int getSize() {
        return this.size;
    }

}
