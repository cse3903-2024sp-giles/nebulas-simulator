package nebulas.simulator;

/**
 *Interpreter - parses instructions passed and operates on the machine to setup for the next state.
 *
 *<p>
 *
 *<p>
 **/
public class Interpreter{
    
    Machine1 m;
    Word currentInstruction;

    public Interpreter(Machine1 machine){
        m = machine; 
    }
    

    /**
     * Takes the machine that was passed at construction and preforms one single instruction
     * returns true to continue running and false if issue with instruction or halt
     */
    public boolean step(){
        
        //Put the current instruction in the CPU and then increment the PC
        
        m.cpu = m.pc;
        //increment PC
        m.pc.add(new Word1(1));
        //set the current insrutction to be used
        currentInstruction = m.cpu;
        

        switch (currentInstruction.toString().substring(0, 4)) {
            case "0001": //add
                add();
                break;
            case "0101": //and
                and();
                break;
            case "0000": //brx
                brx();
                break;
            case "1000": //dbug
                dbug();
                break;
            case "0100": //jsr
                jsr();
                break;
            case "1100": //jsrr
                jsrr();     
                break;
            case "0010": //ld
                ld();
                break;
            case "1010": //ldi
                ldi();
                break;
            case "0110": //ldr
                ldr();
                break;
            case "1110": //lea
                lea();
                break;
            case "1001": //not
                not();
                break;
            case "1101": //ret
                ret();
                break;
            case "0011": //st
                st();
                break;
            case "1011": //sti
                sti();
                break;
            case "0111": //str
                str();
                break;
            case "1111": //trap
                trap();
                //maybe return a value here to indicate that this is a halt
                break;
        
            default:
                System.out.println("[ERROR] Word in CPU is not an instruction + ");
                System.exit(1); 
                break;
        }

        return true;
    }
    

    private void add(){

    }

    private void and(){
        
    }
    
    private void brx(){
        
    }
    
    private void dbug(){
        
    }
    
    private void jsr(){
        
    }
    
    
    private void jsrr(){
        
    }

    
    private void ld(){
        
    }
    
    private void ldi(){
        
    }
    
    
    private void ldr(){
        
    }
    
    private void lea(){
        
    }
    
    private void not(){
        
    }
    
    private void ret(){
        
    }
    
    private void st(){
        
    }
    
    private void sti(){
        
    }
    
    private void str(){
        
    }
    
    private void trap(){
        
    }

}
