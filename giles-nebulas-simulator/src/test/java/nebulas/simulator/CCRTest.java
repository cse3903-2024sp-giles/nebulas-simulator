package nebulas.simulator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CCRTest {
    @Test
    public void testCCR_SetN() {
        CCR ccr = new CCR1();
        ccr.setN();

        assertTrue(ccr.getN());
        assertFalse(ccr.getZ());
        assertFalse(ccr.getP());
    }

    @Test
    public void testCCR_SetZ() {
        CCR ccr = new CCR1();
        ccr.setZ();

        assertFalse(ccr.getN());
        assertTrue(ccr.getZ());
        assertFalse(ccr.getP());
    }

    @Test
    public void testCCR_SetP() {
        CCR ccr = new CCR1();
        ccr.setP();

        assertFalse(ccr.getN());
        assertFalse(ccr.getZ());
        assertTrue(ccr.getP());
    }
}