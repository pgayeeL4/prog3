package edu.prog3;

import edu.prog3.Model.Block;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pratik on 12/3/2016.
 */
public class BlockTest {

    Block b1 = new Block(1024, 0);

    Block b2 = new Block(256, 1024);

    @Test
    public void findBuddyAddress() throws Exception {
        assertEquals(1024, b1.findBuddyAddress());
        assertEquals(1280, b2.findBuddyAddress());
    }

}