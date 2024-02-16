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

}