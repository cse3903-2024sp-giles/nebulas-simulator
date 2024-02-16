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
        registers.setRegister(0, new Word1(100));
        registers.setRegister(1, new Word1(200));
        registers.setRegister(2, new Word1(300));
        registers.setRegister(3, new Word1(400));
        registers.setRegister(4, new Word1(500));
        registers.setRegister(5, new Word1(600));
        registers.setRegister(6, new Word1(700));
        registers.setRegister(7, new Word1(800));
        
        assertTrue(registers.getRegister(0).equals(new Word1(100)));
        assertTrue(registers.getRegister(1).equals(new Word1(200)));
        assertTrue(registers.getRegister(2).equals(new Word1(300)));
        assertTrue(registers.getRegister(3).equals(new Word1(400)));
        assertTrue(registers.getRegister(4).equals(new Word1(500)));
        assertTrue(registers.getRegister(5).equals(new Word1(600)));
        assertTrue(registers.getRegister(6).equals(new Word1(700)));
        assertTrue(registers.getRegister(7).equals(new Word1(800)));
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