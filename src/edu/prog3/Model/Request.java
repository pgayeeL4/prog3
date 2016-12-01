package edu.prog3.Model;

/**
 * Created by Pratik on 12/1/2016
 * a base request, has an ID.
 *
 * All requests should be of subtypes Allocation or Deallocation, not plain Requests
 */
public abstract class Request {

    public Request(int newId) {this.id = newId;}

    private int id;

    public int getId() {
        return this.id;
    }
}
