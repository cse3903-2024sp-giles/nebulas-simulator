package nebulas.simulator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class wordTest {
    //test if two word which are created by same class [Word1()] are same
    @Test
    public void testWord1() {
        Word a = new Word1();

        Word b = new Word1();

        assertEquals(a, b);
    }

    //test if
    @Test
    public void testWord2() {
        Word a = new Word1("1");

        assertEquals(a.getBit(0), true);
    }

}
