package edu.prog3;

import edu.prog3.Helper.BoundedList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Pratik on 12/3/2016.
 */
public class BoundedListTest {

    BoundedList<String> bls = new BoundedList<>(1, 4);

    public void setUp() {
        bls.set(1, "blah1");
        bls.set(2, "blah2");
        bls.set(4, "blah4");
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testBadGet() {
        setUp();

        exception.expect(ArrayIndexOutOfBoundsException.class);
        bls.get(0);
    }

    @Test
    public void testMinGet() throws Exception {
        setUp();
        assertEquals("Did not find blah1 at minIndex", "blah1", bls.get(bls.getMinIndex()));
    }

    @Test
    public void testMaxGet() throws Exception {
        setUp();
        assertEquals("Did not find blah4 at minIndex", "blah4", bls.get(bls.getMaxIndex()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddObject() throws Exception {
        bls.add("blah1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAtIndex() throws Exception {
        bls.add(2, "blah2");
    }

    @Test
    public void testBadSet() throws Exception {
        exception.expect(ArrayIndexOutOfBoundsException.class);
        bls.set(0, "blah");
    }

    @Test
    public void testGoodSet() throws Exception {
        bls.set(1, "test");
        assertEquals("did not set element properly", "test", bls.get(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveAtIndex() throws Exception {
        bls.remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveObject() throws Exception {
        bls.remove("blah1");
    }

}