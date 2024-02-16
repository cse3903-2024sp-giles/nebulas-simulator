package nebulas.simulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InterpreterTest {
    @Test
    public void testInterpreterAdd() {
        Machine1 machine = new Machine1(Machine.MODE.QUIET);
        machine.registers.setRegister(0, new Word1(10));
        machine.registers.setRegister(1, new Word1(20));
        machine.pc = new Word1("0001010000000001");

        Interpreter interpreter = new Interpreter(machine);
        interpreter.step();

        Word expected = new Word1(30);
        Word actual = machine.registers.getRegister(2);
        System.out.println(expected);
        System.out.println(actual);

        assertTrue(actual.equals(expected));
    }
}