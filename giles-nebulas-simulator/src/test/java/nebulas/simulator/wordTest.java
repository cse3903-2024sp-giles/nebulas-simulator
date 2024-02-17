package nebulas.simulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordTest {

    // test if "a" initialized successfully
    @Test
    public void testWord1_2() {
        Word a = new Word1();

        assertEquals(a.getBit(0), false);
        assertEquals(a.getBit(15), false);
    }

    // test if "a" initialized successfully
    @Test
    public void testWord1_3() {
        Word a = new Word1("1");

        assertEquals(a.getBit(0), true);
    }

    // test if "a" initialized successfully
    @Test
    public void testWord1_4() {
        Word a = new Word1("1000000100000001");

        assertEquals(a.getBit(0), true);
        assertEquals(a.getBit(8), true);
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

    // test error case
    // @Test
    // public void testsetBit_3() {
    // Word a = new Word1();
    // boolean x = a.setBit(16, true);

    // assertEquals(a.getBit(16), false);
    // assertEquals(x, false);
    // }

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
    public void testAdd_CorrectBinarySum() {
        Word a = new Word1("11111111"); // 255 in decimal
        Word b = new Word1("00000001"); // 1 in decimal
        Word expected = new Word1("100000000"); // 256 in decimal, but considering 16-bit, it's "0000000100000000"

        Word result = a.add(b);
        assertEquals("The result of adding 255 and 1 should correctly calculate the sum", expected.toInt(),
                result.toInt());
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

    @Test
    public void testAdd_CorrectResult() {
        Word a = new Word1("00000001"); // Binary for 1
        Word b = new Word1("00000001"); // Binary for 1
        Word expected = new Word1("00000010"); // Expected result, binary for 2

        Word result = a.add(b);
        assertEquals("Addition should correctly calculate the sum", expected.toInt(), result.toInt());
    }

    @Test
    public void testAnd_CorrectResult() {
        Word a = new Word1("1111111111111111"); // All bits set
        Word b = new Word1("0000000000000000"); // All bits cleared, assuming this initializes to all 0s
        Word expected = new Word1("0000000000000000"); // AND operation result should be all bits cleared

        Word result = a.and(b);
        assertEquals("AND operation should result in all bits cleared", expected.toInt(), result.toInt());
    }

    @Test
    public void testAnd_2() {
        Word a = new Word1("1111111111111111");
        Word b = new Word1("0000000000000000");
        Word c = new Word1("0000000000000000");

        assertEquals(c.equals(a.and(b)), true);
    }

    @Test
    public void testAnd_ValidInput() {
        // Initialize Word objects with valid 16-bit binary strings
        Word a = new Word1("1111111100000000"); // A 16-bit pattern
        Word b = new Word1("0000111111110000"); // Another 16-bit pattern
        // Expected result of AND operation between a and b
        Word expected = new Word1("0000111100000000"); // Result of ANDing a and b

        Word result = a.and(b);

        assertEquals("The result of the AND operation should match the expected outcome", expected.toInt(),
                result.toInt());
    }

    @Test
    public void testNot_1() {
        Word a = new Word1("1111111111111111");
        Word result = a.not(); // Correct usage without second operand
        Word expected = new Word1("0000000000000000");

        assertEquals(true, result.equals(expected));
    }

    @Test
    public void testNot_2() {
        Word a = new Word1("0000000000000000");
        Word result = a.not(); // Correct usage without second operand
        Word expected = new Word1("1111111111111111");

        assertEquals(true, result.equals(expected));
    }

    @Test
    public void testNot_3() {
        Word a = new Word1("1010101010101010");
        Word result = a.not(); // Correct usage without second operand
        Word expected = new Word1("0101010101010101");

        assertEquals(true, result.equals(expected));
    }

    @Test
    public void testNot_CorrectInversion() {
        Word a = new Word1("0000000000001111"); // Explicitly use a 16-bit representation
        Word result = a.not();
        Word expected = new Word1("1111111111110000"); // Expected inversion

        assertEquals("Inversion should flip all bits", true, result.equals(expected));
    }

    @Test
    public void testAdd_correctBinarySum() {
        // Initializing Word1 objects with binary strings that represent numbers
        Word a = new Word1("00000001"); // Represents the binary number for 1
        Word b = new Word1("00000001"); // Represents the binary number for 1

        // The expected result of adding 1 + 1 in binary
        Word expected = new Word1("00000010"); // Represents the binary number for 2

        // Perform the addition
        Word result = a.add(b);

        // Assert that the result of the addition matches the expected outcome
        assertEquals(expected.toInt(), result.toInt());
    }

    @Test
    public void testSign_Positive_1_Word() {
        Word word = new Word1("0000000000000001");
        assertTrue(word.sign() == 1);
    }

    @Test
    public void testSign_Positive_1() {
        Word word = new Word1("00000001");
        assertTrue(word.sign() == 1);
    }

    @Test
    public void testSign_Positive_1_Int() {
        Word word = new Word1(1);
        assertTrue(word.sign() == 1);
    }

    @Test
    public void testSign_Positive_1000_Int() {
        Word word = new Word1(1000);
        assertTrue(word.sign() == 1);
    }

    @Test
    public void testSign_Negative_1() {
        Word word = new Word1("1111111111111111");
        assertTrue(word.sign() == -1);
    }

    @Test
    public void testSign_Negative_10() {
        Word word = new Word1("1111111111110110");
        assertTrue(word.sign() == -1);
    }

    @Test
    public void testSign_Negative_10_Int() {
        Word word = new Word1(-10);
        assertTrue(word.sign() == -1);
    }

    @Test
    public void testSign_Negative_1000_Int() {
        Word word = new Word1(-1000);
        assertTrue(word.sign() == -1);
    }

    @Test
    public void testSign_Zero() {
        Word word = new Word1("0000000000000000");
        assertTrue(word.sign() == 0);
    }

    @Test
    public void testSign_Zero_Int() {
        Word word = new Word1(0);
        assertTrue(word.sign() == 0);
    }

    @Test
    public void test_bitsToInt_Zero() {
        Word word = new Word1(0);
        assertEquals(word.bitsToInt(0, 15), 0);
        assertEquals(word.bitsToInt(0, 7), 0);
        assertEquals(word.bitsToInt(8, 15), 0);
    }

    @Test
    public void test_bitsToInt_Five() {
        Word word = new Word1(5);
        assertEquals(word.bitsToInt(0, 15), 5);
        assertEquals(word.bitsToInt(0, 7), 5);
        assertEquals(word.bitsToInt(8, 15), 0);
    }

    @Test
    public void test_bitsToInt_Five_InitString() {
        Word word = new Word1("0101");
        assertEquals(word.bitsToInt(0, 15), 5);
        assertEquals(word.bitsToInt(0, 7), 5);
        assertEquals(word.bitsToInt(8, 15), 0);
    }

    @Test
    public void test_bitsToInt_Five_InitStringWord() {
        Word word = new Word1("0000000000000101");
        assertEquals(word.bitsToInt(0, 15), 5);
        assertEquals(word.bitsToInt(0, 7), 5);
        assertEquals(word.bitsToInt(8, 15), 0);
    }

    @Test
    public void test_bitsToInt_TenThousand_InitStringWord() {
        Word word = new Word1("0010011100010000");
        assertEquals(10000, word.bitsToInt(0, 15));
        assertEquals(0, word.bitsToInt(0, 3));
        assertEquals(1, word.bitsToInt(4, 7));
        assertEquals(7, word.bitsToInt(8, 11));
        assertEquals(2, word.bitsToInt(12, 15));
    }

    @Test
    public void test_bitsToInt_FiftyThousand_InitStringWord() {
        Word word = new Word1("1100001101010000");
        assertEquals(50000, word.bitsToInt(0, 15));
        assertEquals(0, word.bitsToInt(0, 3));
        assertEquals(5, word.bitsToInt(4, 7));
        assertEquals(3, word.bitsToInt(8, 11));
        assertEquals(12, word.bitsToInt(12, 15));
    }

    @Test
    public void test_bitsToString_Zero() {
        Word word = new Word1(0);
        assertEquals("0000000000000000", word.bitsToString(0, 15));
        assertEquals("0000", word.bitsToString(0, 3));
        assertEquals("0000", word.bitsToString(4, 7));
        assertEquals("0000", word.bitsToString(8, 11));
        assertEquals("0000", word.bitsToString(12, 15));
    }

    @Test
    public void test_bitsToString_Five() {
        Word word = new Word1(5);
        assertEquals("0000000000000101", word.bitsToString(0, 15));
        assertEquals("0101", word.bitsToString(0, 3));
        assertEquals("0000", word.bitsToString(4, 7));
        assertEquals("0000", word.bitsToString(8, 11));
        assertEquals("0000", word.bitsToString(12, 15));
    }

    @Test
    public void test_bitsToString_TenThousand() {
        Word word = new Word1("0010011100010000");
        assertEquals("0010011100010000", word.bitsToString(0, 15));
        assertEquals("0000", word.bitsToString(0, 3));
        assertEquals("0001", word.bitsToString(4, 7));
        assertEquals("0111", word.bitsToString(8, 11));
        assertEquals("0010", word.bitsToString(12, 15));
    }

    @Test
    public void test_bitsToString_FiftyThousand() {
        Word word = new Word1("1100001101010000");
        assertEquals("1100001101010000", word.bitsToString(0, 15));
        assertEquals("0000", word.bitsToString(0, 3));
        assertEquals("0101", word.bitsToString(4, 7));
        assertEquals("0011", word.bitsToString(8, 11));
        assertEquals("1100", word.bitsToString(12, 15));
    }

    @Test
    public void test_bitsToString_Negative_FiftyThousand() {
        Word word = new Word1(-50000);
        assertEquals("0011110010110000", word.bitsToString(0, 15));
        assertEquals("0000", word.bitsToString(0, 3));
        assertEquals("1011", word.bitsToString(4, 7));
        assertEquals("1100", word.bitsToString(8, 11));
        assertEquals("0011", word.bitsToString(12, 15));
    }

    @Test
    public void test_toInt_Zero() {
        Word word = new Word1(0);
        assertEquals(0, word.toInt());
    }

    @Test
    public void test_toInt_Five() {
        Word word = new Word1(5);
        assertEquals(5, word.toInt());
    }

    @Test
    public void test_toInt_Max() {
        Word word = new Word1(65535);
        assertEquals(65535, word.toInt());
    }

    @Test
    public void test_toInt_Overflow() {
        Word word = new Word1(65536);
        assertEquals(0, word.toInt());
    }
}
