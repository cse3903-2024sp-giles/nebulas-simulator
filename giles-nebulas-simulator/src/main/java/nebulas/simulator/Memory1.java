package nebulas.simulator;

public class Memory1 implements Memory{
    
    Word[] wordsArray = new Word[65536];
    
    public Memory1(){
        
    }
    
    public Word getWord(Word address){
        return wordsArray[address.toInt()];
    }
    
    public void setWord(Word address, Word value){
       wordsArray[address.toInt()] = value; 
    }
}
