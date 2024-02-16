package nebulas.simulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class InterpreterTest {
    Machine1 machine;
    Interpreter interpreter;
    Random random;
    // SR1 = 0, SR2 = 1, DR = 2
    String addOp = "0001010000000001";
    String andOp = "0101010000000001";
    // SR = 0, DR = 1
    String notOp = "1001001000000000";

    @Before
    public void initialize() {
        machine = new Machine1(Machine.MODE.QUIET);
        interpreter = new Interpreter(machine);
        random = new Random();
    }

    public boolean checkCCR(int expectedSign, Machine1 m) {
        System.out.println("Checking CCR:");
        boolean check;
        if (expectedSign > 0) {
            check = m.ccr.getP() && !m.ccr.getN() && !m.ccr.getZ();
            System.out.println("Positive: " + check);
        } else if (expectedSign < 0) {
            check = !m.ccr.getP() && m.ccr.getN() && !m.ccr.getZ();
            System.out.println("Negative: " + check);
        } else {
            check = !m.ccr.getP() && !m.ccr.getN() && m.ccr.getZ();
            System.out.println("Zero: " + check);
        }
        return check;
    }

    /**
     * ------------- ADD -------------
     */

    @Test
    public void testInterpreterAdd_10_20() {
        machine.memory.setWord(new Word1(), new Word1(addOp));

        machine.registers.setRegister(0, new Word1(10));
        machine.registers.setRegister(1, new Word1(20));

        interpreter.step();

        Word expected = new Word1(30);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(1, machine));
    }

    @Test
    public void testInterpreterAdd_345_23456() {
        machine.memory.setWord(new Word1(), new Word1(addOp));

        machine.registers.setRegister(0, new Word1(345));
        machine.registers.setRegister(1, new Word1(23456));

        interpreter.step();

        Word expected = new Word1(23801);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(1, machine));
    }

    @Test
    public void testInterpreterAdd_N32_P20() {
        machine.memory.setWord(new Word1(), new Word1(addOp));

        machine.registers.setRegister(0, new Word1(-32));
        machine.registers.setRegister(1, new Word1(20));

        interpreter.step();

        Word expected = new Word1(-12);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(-1, machine));
    }

    @Test
    public void testInterpreterAdd_N32_P43() {
        machine.memory.setWord(new Word1(), new Word1(addOp));

        machine.registers.setRegister(0, new Word1(-32));
        machine.registers.setRegister(1, new Word1(43));

        interpreter.step();

        Word expected = new Word1(11);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(1, machine));
    }

    @Test
    public void testInterpreterAdd_Overflow() {
        machine.memory.setWord(new Word1(), new Word1(addOp));

        machine.registers.setRegister(0, new Word1(32767));
        machine.registers.setRegister(1, new Word1(32767));

        interpreter.step();

        Word expected = new Word1(65534);
        Word actual = machine.registers.getRegister(2);
        System.out.println("Expecting " + expected + " got " + actual);

        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(-1, machine));
    }

    /**
     * ------------- AND -------------
     */

    @Test
    public void testInterpreterAnd_Bit() {
        machine.memory.setWord(new Word1(), new Word1(andOp));

        // 0 & 0
        machine.registers.setRegister(0, new Word1(0));
        machine.registers.setRegister(1, new Word1(0));

        interpreter.step();

        Word expected = new Word1(0);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));

        // 0 & 1
        machine.registers.setRegister(0, new Word1(0));
        machine.registers.setRegister(1, new Word1(1));

        interpreter.step();
        expected = new Word1(0);
        actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));

        // 1 & 0
        machine.registers.setRegister(0, new Word1(1));
        machine.registers.setRegister(1, new Word1(0));

        interpreter.step();
        expected = new Word1(0);
        actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));

        // 1 & 1
        machine.registers.setRegister(0, new Word1(1));
        machine.registers.setRegister(1, new Word1(1));

        interpreter.step();
        expected = new Word1(1);
        actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testInterpreterAnd_10_20() {
        machine.memory.setWord(new Word1(), new Word1(andOp));
        machine.registers.setRegister(0, new Word1(10));
        machine.registers.setRegister(1, new Word1(20));

        interpreter.step();

        Word expected = new Word1(0);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(0, machine));
    }

    @Test
    public void testInterpreterAnd_29_43() {
        machine.memory.setWord(new Word1(), new Word1(andOp));
        machine.registers.setRegister(0, new Word1(29));
        machine.registers.setRegister(1, new Word1(43));

        interpreter.step();

        Word expected = new Word1(9);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(1, machine));
    }

    @Test
    public void testInterpreterAnd_Overflow() {
        machine.memory.setWord(new Word1(), new Word1(andOp));

        machine.registers.setRegister(0, new Word1(99589));
        machine.registers.setRegister(1, new Word1(41506));

        interpreter.step();

        Word expected = new Word1(32768);
        Word actual = machine.registers.getRegister(2);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(-1, machine));
    }

    @Test
    public void testInterpreterAnd_Random_Different_Lengths() {
        machine.memory.setWord(new Word1(), new Word1(andOp));

        int num1 = random.nextInt(30000) + 1000;
        int num2 = random.nextInt(999);
        machine.registers.setRegister(0, new Word1(num1));
        machine.registers.setRegister(1, new Word1(num2));
        System.out.println(num1 + " & " + num2);

        interpreter.step();

        int result = num1 & num2;
        Word expected = new Word1(result);
        Word actual = machine.registers.getRegister(2);
        System.out.println("Expecting " + expected + " got " + actual);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(result, machine));
    }

    @Test
    public void testInterpreterNot_10() {
        machine.memory.setWord(new Word1(), new Word1(notOp));
        machine.registers.setRegister(0, new Word1(10));

        interpreter.step();

        Word expected = new Word1(-11);
        Word actual = machine.registers.getRegister(1);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(-1, machine));
    }

    /**
     * ------------- NOT -------------
     */

    @Test
    public void testInterpreterNot_N200() {
        machine.memory.setWord(new Word1(), new Word1(notOp));
        machine.registers.setRegister(0, new Word1(-200));

        interpreter.step();

        Word expected = new Word1(199);
        Word actual = machine.registers.getRegister(1);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(1, machine));
    }

    @Test
    public void testInterpreterNot_32767() {
        machine.memory.setWord(new Word1(), new Word1(notOp));
        machine.registers.setRegister(0, new Word1(32767));

        interpreter.step();

        Word expected = new Word1(-32768);
        Word actual = machine.registers.getRegister(1);
        assertTrue(actual.equals(expected));
        assertTrue(checkCCR(-1, machine));
    }
}