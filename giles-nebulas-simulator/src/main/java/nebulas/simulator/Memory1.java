package nebulas.simulator;

public class Memory1 implements Memory{
    
    Word[] wordsArray = new Word1[65536];
    
    public Memory1(){
        
        for(int i =0; i < wordsArray.length; i++){
            wordsArray[i] = new Word1();
        }
    }
    
    public Word getWord(Word address){
        int index = address.toInt();
        
        if(index >= 0 && index < wordsArray.length){

            return wordsArray[index];
        } else{
            throw new IllegalArgumentException("Cannot read address out of bounds");
        }
    }

    
    public void setWord(Word address, Word value){
        int index = address.toInt();
        
        if(index >= 0 && index < wordsArray.length){
            
            wordsArray[index] = value;

        } else{
            throw new IllegalArgumentException("Cannot write address out of bounds");
        }
    }
}
