package nebulas.simulator;

import java.util.BitSet;

public class Word1 implements Word{
    
    public BitSet w;

    //Word1 class constructor, input should be string, then transform into bitset
    public Word1() {
        this.w = new BitSet(16);
    }

    public Word1(int input) {
        this.w = new BitSet(16);
        for (int i = 0; i < 16; i++) {
            if ((input & (1 << i)) != 0) {
                this.w.set(i);
            }
        }
    }

    public Word1(String input) {
        String pattern = "[01]+";
        if (input.matches(pattern)) {

            this.w = new BitSet(16);

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '1') {
                    this.w.set(input.length() - 1 -i);
                }
            }
        } else {
            System.out.println("String does not contain only 1s and 0s.");
        }
    }

    @Override
    public boolean getBit(int position) {
        if (position < 16) {
            return this.w.get(position);
        } else {
            System.out.println("error position");
            return false;
        }
    }

    @Override
    public boolean setBit(int position, boolean value) {
        if (position < 16) {
            if (value) {
                this.w.set(position); // start from 0
            } else {
                this.w.clear(position); // start from 0
            }
            return true;
        } else {
            System.out.println("error position");
            return false;
        }
    }

    @Override
    public boolean equals(Word b) {
        Word1 wordB = (Word1) b;

        return this.w.equals(wordB.w);
    }

    @Override
    public Word add(Word b) {
        
        Word1 wordB = (Word1) b;

        //convert to ints
        int valueA = this.toInt();

        int valueB = wordB.toInt();

        int sum = valueA + valueB;

        //convert back after addition

        Word1 result = new Word1(sum);

        return result;

    }

    @Override
    public Word and(Word b) {
        Word1 wordB = (Word1) b;
        Word1 result = new Word1(); // Create a new Word1 object for the result
        result.w = (BitSet) this.w.clone(); // Clone this.w to keep the operation non-destructive
        result.w.and(wordB.w); // Perform bitwise AND with wordB's BitSet
        return result;
    }


    @Override
    public Word not() {
      
        Word1 result = new Word1();
        result.w = (BitSet) this.w.clone();
        result.w.flip(0,16);
        return result;
    }

    public int toInt(){

        int intVal = 0;

        for(int i = 0; i < 16; i++){
            intVal += (this.w.get(i) ? 1 : 0) << i;
        }

        return intVal;
    }

}
