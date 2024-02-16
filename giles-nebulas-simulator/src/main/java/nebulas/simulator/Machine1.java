package nebulas.simulator;

public class Machine1 {
    

    //init our registers
    CCR ccr;
    
    Registers registers;

    //int our cpua nd pc
    Word pc;

    Word cpu;

    //memory
    Memory memory;
    

    public Machine1(){

        ccr = new CCR1();

        registers = new Registers1();

        pc = new Word1();

        cpu = new Word1();
        
        memory = new Memory1();

    }

}