package nebulas.simulator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class wordTest {
    //test if two word which are created by same class [Word1()] are same
    @Test
    public void testWord1_1() {
        Word a = new Word1();

        Word b = new Word1();

        assertEquals(a, b);
    }

    //test if "a" initialized successfully
    @Test
    public void testWord1_2() {
        Word a = new Word1();

        assertEquals(a.getBit(0), false);
        assertEquals(a.getBit(15), false);
    }

    //test if "a" initialized successfully
    @Test
    public void testWord1_3() {
        Word a = new Word1("1");

        assertEquals(a.getBit(0), true);
    }

    //test if "a" initialized successfully
    @Test
    public void testWord1_4() {
        Word a = new Word1("1000000100000001");

        assertEquals(a.getBit(0), true);
        assertEquals(a.getBit(7), true);
        assertEquals(a.getBit(15), true);
    }

    @Test
    public void testgetBit_1() {
        Word a = new Word1("1000000100000001");

        assertEquals(a.getBit(17), false);
    }

}
