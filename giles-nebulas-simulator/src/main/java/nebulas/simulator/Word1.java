package nebulas.simulator;

import java.util.BitSet;

public class Word1 implements Word{
    
    public BitSet w = new BitSet(16);

    //Word1 class constructor, input should be string, then transform into bitset
    public Word1() {
        this.w = new BitSet(16);
    }

    public Word1(String input) {
        String pattern = "[01]+";
        if (input.matches(pattern)) {

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '1') {
                    this.w.set(i);
                }
            }
        } else {
            System.out.println("String does not contain only 1s and 0s.");
        }
    }

    @Override
    public boolean getBit(int position) {
        if (position < 17) {
            return this.w.get(position);
        } else {
            System.out.println("error position");
            return false;
        }
    }

    @Override
    public boolean setBit(int position, boolean value) {
        if (position < 17) {
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
        Word1 wordA = new Word1();

        int lengthA = this.w.length();
        int lengthB = wordB.w.length();
        if (lengthA + lengthB <= 16) {
            wordA.w = (BitSet) this.w.clone();
            for (int i = 0; i < lengthB; i++) {
                boolean bit = wordB.w.get(i);
                wordA.setBit(lengthA + i, bit);
            }
        } else {
            System.out.print("error length");
        }

        return wordA;
    }

    @Override
    public Word and(Word b) {
        Word1 wordA = new Word1();
        Word1 wordB = (Word1) b;

        int lengthA = this.w.length();
        int lengthB = wordB.w.length();
        if (lengthA + lengthB <= 32) {
            wordA.w = (BitSet) this.w.clone();
            wordA.w.and(wordB.w);
        } else {
            System.out.print("error length");
        }

        return wordA;
    }

    @Override
    public Word not(Word b) {
        Word1 wordA = new Word1();
        Word1 wordB = (Word1) b;

        int lengthA = this.w.length();
        int lengthB = wordB.w.length();
        if (lengthA + lengthB <= 32) {
            wordA.w = (BitSet) this.w.clone();
            wordA.w.andNot(wordB.w);
        } else {
            System.out.print("error length");
        }

        return wordA;
    }

    @Override
    public int toInt(){

        int intVal = 0;

        for(int i = 0; i < 16; i++){
            intVal += (this.w.get(i) ? 1 : 0) << i;
        }

        return intVal;
    }

}
