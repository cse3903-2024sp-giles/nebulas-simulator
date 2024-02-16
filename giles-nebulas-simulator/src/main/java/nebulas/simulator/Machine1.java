package nebulas.simulator;

public class Machine1 {
    
    public Machine.MODE mode;
    

    //init our registers
    public CCR ccr;
    
    public Registers registers;

    //int our cpua nd pc
    public Word pc;

    public Word cpu;

    //memory
    public Memory memory;
    

    public Machine1(Machine.MODE runningModeInput){
        
        mode = runningModeInput;

        ccr = new CCR1();

        registers = new Registers1();

        pc = new Word1();

        cpu = new Word1();
        
        memory = new Memory1();

    }

}