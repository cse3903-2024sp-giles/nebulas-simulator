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
        
        if (currentInstruction.getBit(5)) {
            //This is the second add 
            
            //setup registers
            Word sr1 = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));
            int dr = currentInstruction.bitsToInt(9, 11);
            //get additive
            Word additive = new Word1(currentInstruction.bitsToInt(0, 4), currentInstruction.getBit(4));
            
            //do addition
            Word result = sr1.add(additive);
            
            m.registers.setRegister(dr, result);
            

            //set CCR
            if(result.sign() == 1){
                m.ccr.setP();
            }else if (result.sign() == -1) {
                m.ccr.setN();
            }else{
                m.ccr.setZ();
            }
            //done

            
        }else{
            //assuming its the first add
            
            int dr = currentInstruction.bitsToInt(9, 11);
            Word sr1 = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));
            Word sr2 = m.registers.getRegister(currentInstruction.bitsToInt(0, 2));

            Word result = sr1.add(sr2);

            m.registers.setRegister(dr, result);
            

            //set CCR
            if(result.sign() == 1){
                m.ccr.setP();
            }else if (result.sign() == -1) {
                m.ccr.setN();
            }else{
                m.ccr.setZ();
            }
            //done



        }

    }

    private void and(){
        
        if (currentInstruction.getBit(5)) {
            //second and
            //

            int dr = currentInstruction.bitsToInt(9, 11);
            Word sr = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));

            Word andy = new Word1(currentInstruction.bitsToInt(0, 4), currentInstruction.getBit(4));
            

            Word result = sr.and(andy);

            m.registers.setRegister(dr, result);

             //set CCR
            if(result.sign() == 1){
                m.ccr.setP();
            }else if (result.sign() == -1) {
                m.ccr.setN();
            }else{
                m.ccr.setZ();
            }
                       

        }else{
            //first and
            int dr = currentInstruction.bitsToInt(9, 11);
            Word sr = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));
            Word sr2 = m.registers.getRegister(currentInstruction.bitsToInt(0, 2));
            
            Word result = sr.and(sr2);
            m.registers.setRegister(dr, result);

             //set CCR
            if(result.sign() == 1){
                m.ccr.setP();
            }else if (result.sign() == -1) {
                m.ccr.setN();
            }else{
                m.ccr.setZ();
            }
                 



        }
        
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
        int dr = currentInstruction.bitsToInt(9, 11);
        
        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);

        Word address = new Word1(page + index);

        Word value  = m.memory.getWord(address);

        m.registers.setRegister(dr, value);
        

          //set CCR
          if(value.sign() == 1){
            m.ccr.setP();
        }else if (value.sign() == -1) {
            m.ccr.setN();
        }else{
            m.ccr.setZ();
        }

        
    }
    
    private void ldi(){
        int dr = currentInstruction.bitsToInt(9, 11);

        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);
        
        Word  firstAddress = new Word1(page + index);

        Word secondAddress = m.memory.getWord(firstAddress);

        Word value = m.memory.getWord(secondAddress);

        m.registers.setRegister(dr, value);
        

          //set CCR
        if(value.sign() == 1){
            m.ccr.setP();
        }else if (value.sign() == -1) {
            m.ccr.setN();
        }else{
            m.ccr.setZ();
        }
        
    }
    
    
    private void ldr(){
        int dr = currentInstruction.bitsToInt(9, 11);
        Word br  = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));

        Word index = new Word1(currentInstruction.bitsToString(0, 5));
        Word address = br.add(index);
        

        Word value = m.memory.getWord(address);

        m.registers.setRegister(dr, value);
          //set CCR
        if(value.sign() == 1){
            m.ccr.setP();
        }else if (value.sign() == -1) {
            m.ccr.setN();
        }else{
            m.ccr.setZ();
        }
        

    }
    
    private void lea(){
        
    }
    
    private void not(){
        //sr = 6,7,8
        //dr = 9,10,11
        Word sr = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));
        int dr = currentInstruction.bitsToInt(9, 11);
        
        Word result = sr.not();
        m.registers.setRegister(dr, result);
        

        //set CCR
       if(result.sign() == 1){
           m.ccr.setP();
       }else if (result.sign() == -1) {
           m.ccr.setN();
       }else{
           m.ccr.setZ();
       }
            
        
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
