package nebulas.simulator;

/**
 *Registers - The regular general registers each 2 bytes, ie the size of the a Word
 *
 *<p>
 *Contains methods to get and set each register
 *<p>
 **/
public interface Registers{

    /**
     * Get the desired register value
     *@param registerNumber the number of the register that you want
     *@return A word object that contains the value of the register*/
    public Word getRegister(int registerNumber);

    /**
     * Set the desired register.
     *@param registerNumber The register that you want to set
     *@param value the value as a Word object that you want to set*/
    public void setRegister(int registerNumber, Word value);

}
