package nebulas.simulator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MemoryTest {

    @Test
    public void testMemory() {
        Word address = new Word1(0);
        Memory memory = new Memory1();
        memory.setWord(address, new Word1(10));

        assertTrue(memory.getWord(address).equals(new Word1(10)));
    }

    @Test
    public void testMemory_Addr_100() {
        Word address = new Word1(100);
        Memory memory = new Memory1();
        memory.setWord(address, new Word1(10));

        assertTrue(memory.getWord(address).equals(new Word1(10)));
    }

    @Test
    public void testMemory_Addr_Max_String() {
        Word address = new Word1("1111111111111111");
        Memory memory = new Memory1();
        memory.setWord(address, new Word1(10000));

        assertTrue(memory.getWord(address).equals(new Word1(10000)));
    }

    @Test
    public void testMemory_Addr_Max_Int() {
        // TODO: 65536 and above shouldn't work?
        Word address = new Word1(65536);
        Memory memory = new Memory1();
        memory.setWord(address, new Word1(10));

        assertTrue(memory.getWord(address).equals(new Word1(10)));
    }

}