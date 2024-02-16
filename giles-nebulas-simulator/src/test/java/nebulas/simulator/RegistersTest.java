package nebulas.simulator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegistersTest {
    @Test
    public void testRegister_SET_GET_0_1() {
        Registers registers = new Registers1();
        registers.setRegister(0, new Word1(100));
        registers.setRegister(1, new Word1(200));

        assertTrue(registers.getRegister(0).equals(new Word1(100)));
        assertTrue(registers.getRegister(1).equals(new Word1(200)));
    }

    @Test
    public void testRegister_SET_GET_ALL() {
        Registers registers = new Registers1();
        for (int i = 0; i < 8; i++) {
            registers.setRegister(i, new Word1(i * 100));
        }

        for (int i = 0; i < 8; i++) {
            assertTrue(registers.getRegister(i).equals(new Word1(i * 100)));
        }
    }

    @Test
    public void testRegister_SET_GET_replace() {
        Registers registers = new Registers1();
        registers.setRegister(0, new Word1(100));
        registers.setRegister(1, new Word1(200));
        registers.setRegister(0, new Word1(300));

        assertTrue(registers.getRegister(0).equals(new Word1(300)));
        assertTrue(registers.getRegister(1).equals(new Word1(200)));
    }
}