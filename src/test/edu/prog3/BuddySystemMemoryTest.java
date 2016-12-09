package edu.prog3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pratik on 12/7/2016.
 */
public class BuddySystemMemoryTest {

    BuddySystemMemory bsd = new BuddySystemMemory(128, 1024);

    @Test
    public void allocate() throws Exception {

    }

    @Test
    public void deallocate() throws Exception {

    }

    @Test
    public void findList() throws Exception {

    }

    @Test
    public void nextPowerTwo() throws Exception {

        assertEquals(128, BuddySystemMemory.nextPowerTwo(127));
        assertEquals(256, BuddySystemMemory.nextPowerTwo(250));
        assertEquals(1024, BuddySystemMemory.nextPowerTwo(900));
        assertEquals(128, BuddySystemMemory.nextPowerTwo(128));
        assertEquals(128, BuddySystemMemory.nextPowerTwo(63));

    }

}