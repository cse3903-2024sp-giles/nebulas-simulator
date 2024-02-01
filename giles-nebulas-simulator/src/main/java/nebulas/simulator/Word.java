package nebulas.simulator;

/**
 * Word - The smallest addressable piece of data in the simulator.
 *<p>
 *Words are the smallest addressable type. Contains methods for manipulating a specific point in memory.
 *Memory is an array of these words. The PC is also type word.
 *<p>
 *@see Machine*/
public interface Word{


    Word add();

    //We might add these later
    //byte getFirstByte();

    //byte getSecondByte();

    boolean getBit(int position);

    boolean setBit(int position, boolean value);

    boolean equals(Word b);



}
