package nebulas.simulator;

/**
 *Memory - An array of words with methods to get and set based on an address. The address being of type word as well.
 *
 *<p>
 *Can get and set word values at a given memory address. The address passed should be in the form of a word.
 *<p>
 **/
public interface Memory{

    /**
     * Gets the word at an address
     *@param address The address in a word object
     **/
    public Word getWord(Word address);

    /**
     * Sets the word at a given address and word value
     *@param address The address in the word object
     *@param value The value to set at the address provided*/
    public void setWord(Word address, Word value);

}
