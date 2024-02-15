package nebulas.simulator;

import org.junit.Test;

import static org.junit.Assert.*;


public class wordTest {

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

    @Test
    public void testsetBit_1() {
        Word a = new Word1("1000000100000001");
        boolean x = a.setBit(0, false);
        boolean y = a.setBit(7, false);
        boolean z = a.setBit(15, false);

        assertEquals(a.getBit(0), false);
        assertEquals(a.getBit(7), false);
        assertEquals(a.getBit(15), false);
        assertEquals(x, true);
        assertEquals(y, true);
        assertEquals(z, true);
    }

    @Test
    public void testsetBit_2() {
        Word a = new Word1();
        boolean x = a.setBit(0, true);
        boolean y = a.setBit(7, true);
        boolean z = a.setBit(15, true);

        assertEquals(a.getBit(0), true);
        assertEquals(a.getBit(7), true);
        assertEquals(a.getBit(15), true);
        assertEquals(x, true);
        assertEquals(y, true);
        assertEquals(z, true);
    }

    //test error case
   // @Test
    //public void testsetBit_3() {
      //  Word a = new Word1();
        //boolean x = a.setBit(16, true);

        //assertEquals(a.getBit(16), false);
        //assertEquals(x, false);
    //}

    @Test
    public void testEquals_1() {
        Word a = new Word1();
        Word b = new Word1();

        assertEquals(a.equals(b), true);
    }

    @Test
    public void testEquals_2() {
        Word a = new Word1("1000000000000001");
        Word b = new Word1("1000000000000001");

        assertEquals(a.equals(b), true);
    }

    @Test
    public void testEquals_3() {
        Word a = new Word1("1000000100000001");
        Word b = new Word1("1000000000000001");

        assertEquals(a.equals(b), false);
    }

    @Test
    public void testAdd_1() {
        Word a = new Word1("11111111");
        Word b = new Word1("11111111");
        Word c = new Word1("1111111111111111");

        assertEquals(c.equals(a.add(b)), true);
    }

    @Test
    public void testAdd_2() {
        Word a = new Word1("00000000");
        Word b = new Word1("11111111");
        Word c = new Word1("11111111");

        assertEquals(c.equals(a.add(b)), true);
    }

    @Test
    public void testAdd_3() {
        Word a = new Word1();
        Word b = new Word1("11111111");
        Word c = new Word1("11111111");

        assertEquals(c.equals(a.add(b)), true);
    }

    @Test
    public void testAnd_4() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1();
        Word c = new Word1("1111111111111111");

        assertEquals(c.equals(a.add(b)), true);
    }

    //test error case
    @Test
    public void testAdd_5() {
        Word a = new Word1("000000001");
        Word b = new Word1("11111111");
        Word c = new Word1();

        assertEquals(c.equals(a.add(b)), true);
    }

    @Test
    public void testAnd_1() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1();
        Word c = new Word1("1111111111111111");

        assertEquals(c.equals(a.and(b)), false);
    }

    @Test
    public void testAnd_2() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1("0000000000000000");
        Word c = new Word1("0000000000000000");

        assertEquals(c.equals(a.and(b)), true);
    }

    //test error case
    @Test
    public void testAnd_3() {
        Word a = new Word1("11111111111111111");
        Word b = new Word1("1000000000000001");
        Word c = new Word1();

        assertEquals(c.equals(a.and(b)), true);
    }

    @Test
    public void testNot_1() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1("1000000000000001");
        Word c = new Word1("0111111111111110");

        assertEquals(c.equals(a.not(b)), true);
    }

    @Test
    public void testNot_2() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1("1111111111111111");
        Word c = new Word1("0000000000000000");

        assertEquals(c.equals(a.not(b)), true);
    }

    @Test
    public void testNot_3() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1("0000000000000000");
        Word c = new Word1("1111111111111111");

        assertEquals(c.equals(a.not(b)), true);
    }

    //test error case
    @Test
    public void testNot_4() {
        Word a = new Word1("11111111111111111");
        Word b = new Word1("1000000000000001");
        Word c = new Word1();

        assertEquals(c.equals(a.not(b)), true);
    }
}
