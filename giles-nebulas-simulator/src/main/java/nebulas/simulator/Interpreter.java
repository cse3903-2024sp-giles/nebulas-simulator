package nebulas.simulator;

import java.util.Random;
import java.util.Scanner;

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
        
        String opCode = currentInstruction.bitsToString(12, 15);
        
        System.out.println("Current opCode" + opCode);

        switch (opCode) {
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
                System.out.println("[ERROR] Word in CPU is not an instruction" + opCode);
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
            
            System.out.println("ADD RESULT| " + result.toString());

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
        
        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);

        Word address = new Word1(page + index);

        //if ANY
        if(
            (currentInstruction.getBit(11) && m.ccr.getN())
            ||
            (currentInstruction.getBit(10) && m.ccr.getZ())
            ||
            (currentInstruction.getBit(9) && m.ccr.getP())
        ){
            //branch
            //
            m.pc = address;
        }        
    }
    
    private void dbug(){
        
        System.out.println("PC: " + m.pc.bitsToString(0, 15));
        
        for( int i = 0; i < 8; i++){
            System.out.println("Register " + Integer.toString(i) + " : " + m.registers.getRegister(i).bitsToString(0, 15));
        }

        System.out.println("CCR: N[" + String.valueOf(m.ccr.getN()) + "] " + "Z[" + String.valueOf(m.ccr.getZ()) + "] " + "P[" + String.valueOf(m.ccr.getP()) + "]");
        
    }
    
    private void jsr(){
        
        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);

        Word address = new Word1(page + index);

        if (currentInstruction.getBit(11)) {
            //jump
            m.registers.setRegister(7, m.pc);
            m.pc = address;
        }else{
            m.pc = address;
        }
        
    }
    
    
    private void jsrr(){
        
        Word br = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));

        Word index = new Word1(currentInstruction.bitsToInt(0, 5));
        Word address = br.add(index);

        if (currentInstruction.getBit((11))) {
            //jump 
            m.registers.setRegister(7, m.pc);
            m.pc = address;
            
        }else{
            m.pc = address;
        }
        
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
        
        int dr = currentInstruction.bitsToInt(9, 11);
        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);

        Word value = new Word1(page + index);
        

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
        
        m.pc = m.registers.getRegister(7);
        
    }
    
    private void st(){

        Word value  = m.registers.getRegister(currentInstruction.bitsToInt(9, 11)); 
        
        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);

        Word address = new Word1(page + index);

        m.memory.setWord(address, value);
    }
    
    private void sti(){
        Word value = m.registers.getRegister(currentInstruction.bitsToInt(9, 11));

        String page = m.pc.bitsToString(9, 15);
        String index = currentInstruction.bitsToString(0, 8);

        Word address = new Word1(page + index);

        m.memory.setWord(address, value);
        
    }
    
    private void str(){
        
        Word value = m.registers.getRegister(currentInstruction.bitsToInt(9, 11));

        Word br = m.registers.getRegister(currentInstruction.bitsToInt(6, 8));
        
        Word index = new Word1(currentInstruction.bitsToString(0, 5));
        Word address = br.add(index);

        m.memory.setWord(address, value);
        
    }
    
    private void trap(){
        
        String vector = currentInstruction.bitsToString(0, 7);
        System.out.print(vector);


        switch (vector) {
            case "00100001": //out
                
                Word reg = m.registers.getRegister(0);
                int cha = reg.bitsToInt(0, 7);

                char value = (char) cha;

                System.out.print(value);
                
                break;
            case "00100010": //puts
                
                Word address = m.registers.getRegister(0);
                
                Word charVal = m.memory.getWord(address);



                //while not a null char
                while ((char) charVal.bitsToInt(0, 7) != '\0') {
                    
                    System.out.print((char) charVal.bitsToInt(0, 7));
                    
                    //incr address
                    address = address.add(new Word1(1));
                    
                    charVal = m.memory.getWord(address);
                    
                }
                break;
            case "00100011": //in
                
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a character: ");

                char inputChar = scanner.next().charAt(0);
                
                System.out.println("Entered: " + inputChar);
                

                //take this char and put in r0

                Word result = new Word1((int) inputChar);
                
                m.registers.setRegister(0, result);
                
                scanner.close();
                
                break;
            case "00100101": //halt
                             //
                System.out.println("[HALT]");
                System.exit(0);
                break;
            case "00110001": //outN
                
                int val = m.registers.getRegister(0).bitsToInt(0, 15);
                
                System.out.print(val);

                break;
            case "00110011": //Inn
                
                Scanner scanner2 = new Scanner((System.in));
                System.out.print("Enter a decimal number: ");

                int num = scanner2.nextInt(0);
                
                System.out.println("Entered: " + num);
                
                //store the 
                //
                
                Word res = new Word1(num);
                
                m.registers.setRegister(0, res);

                

                scanner2.close();

                break;
            case "01000011": //rnd
                
                Random random = new Random();
                int randomInt = random.nextInt(65501);
                m.registers.setRegister(0, new Word1(randomInt));
                break;
            default:
                break;
        }
    }

}
