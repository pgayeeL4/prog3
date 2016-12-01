package edu.prog3.Model;

/**
 * used to denote a request for de-allocation, extends a request but doesn't add anything new
 */
public class DeallocationRequest extends Request {

    public DeallocationRequest(int id) {
        super(id);
    }
}
