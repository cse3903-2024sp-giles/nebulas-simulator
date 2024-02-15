package nebulas.simulator;

/**
 * Word - The smallest addressable piece of data in the simulator.
 * <p>
 * Words are the smallest addressable type. Contains methods for manipulating a
 * specific point in memory. Memory is an array of these words. The PC is also
 * type word.
 * <p>
 *
 * @see Machine
 */
public interface Word {

    boolean getBit(int position); //get the bit on the position

    boolean setBit(int position, boolean value); //set the bit on the position to the value

    boolean equals(Word b); //check if two words are equal

    Word add(Word b); //add the current word to the argument and return the result

    Word and(Word b); //and this word and the argument and return the result

    Word not(); //not this word and return the result

    int toInt();
}

