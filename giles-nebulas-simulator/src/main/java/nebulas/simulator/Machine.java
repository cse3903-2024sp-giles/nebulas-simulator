package nebulas.simulator;

/**
 * Machine - Contains the current state of the machine.
 *
 *<p>
 *This contains all parts of the machine that should be simulated. Memory, PC, CPU, and both CCR registers and General Registers.
 *This should be treated like a state machine. It doesn't have methods to do any of the simulation, only to get and set its parts.
 *<p>
 **/
public interface Machine{


    /**
     * Sets the program counter with the provided word and returns the old pc.
     *@param newPC Word object that becomes the new PC
     *@return The old PC value*/
    public Word setPC(Word newPC);

    /**
     *Gets and returns the current pc
     *@return The currect PC value*/
    public Word getPC();

    /**
     * Sets a register with a word value
     *@param registerNumber - The register to be set
     *@param word - The word that the new register will be set to */
     public void setRegister(int registerNumber, Word word);

    /**
     * Gets and returns the register requested
     *@param registerNumber - The register that should be returned
     *@return The value of the register in a word*/
    public Word getRegister(int registerNumber);

    /** TODO */
    public void setCCR();

    /** TODO */
    public boolean getCCR();
}
