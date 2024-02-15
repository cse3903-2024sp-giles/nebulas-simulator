package nebulas.simulator;

public class Machine1 {
    

    //init our registers
    CCR ccr = new CCR1();
    
    Registers registers = new Registers1();

    //int our cpua nd pc
    Word pc = new Word1();

    Word cpu = new Word1();

    //memory
    Memory memory = new Memory1();
    

    public Machine1(){
        
    }

}