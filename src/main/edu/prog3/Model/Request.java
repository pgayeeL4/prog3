package edu.prog3.Model;

/**
 * 
 * Pratik Gayee pgayee
 * Jonathan Hale jonathanhale
 * Stephanie Akapkoun sakpakoun
 *
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
