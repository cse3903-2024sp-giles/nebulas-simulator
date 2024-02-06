package nebulas.simulator;
import java.util.BitSet; 
/**
 * Word - The smallest addressable piece of data in the simulator.
 *<p>
 *Words are the smallest addressable type. Contains methods for manipulating a specific point in memory.
 *Memory is an array of these words. The PC is also type word.
 *<p>
 *@see Machine*/
public interface Word{
    

    //We might add these later
    //byte getFirstByte();

    //byte getSecondByte();

    boolean getBit(int position);   //get the bit on the position 

    boolean setBit(int position, boolean value);    //set the bit on the position to the value

    boolean equals(Word b); //check if two words are equal

}

class Word1 implements Word {
    
    BitSet w = new BitSet();
    .
    //Word1 class constructor, input should be string, then transform into bitset
    Word(String input){
        String pattern = "[01]+";
        if (input.matches(pattern)) {
            String[] str = input.split("");
            for(int i = 0;i < input.length();i++) {
                if(str[i].equals("1")) {
                    this.w.set(i);
                }
            }
        } else {
            System.out.println("String does not contain only 1s and 0s.");
        }              
    }
    
    public boolean getBit(int position) {
        if(position < 17 && position >= 0) {
            return this.w.get(position-1);
        }else {
            System.out.println("error position");
            return false;
        }        
    }

    public boolean setBit(int position, boolean value) {
        if(position < 17 && position >= 0) {
            if (value) {
                this.w.set(position - 1); // start from 0
            }else {
                this.w.clear(position - 1); // start from 0
            }
            return true;
        }else {
            System.out.println("error position");
            return false;
        }
    }

    public boolean equals(Word b) {
        return this.w.equals(b.w);
    }
}