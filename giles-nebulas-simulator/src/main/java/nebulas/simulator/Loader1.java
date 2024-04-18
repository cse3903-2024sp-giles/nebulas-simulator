package nebulas.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loader1 {

    private int initLoadAddr;
    private int programLength;
    private int startAddr;
    private String name;
    
    public void loadMachine(Machine1 machine, String filepath) throws IOException{

        if (checkForIllegalCharacters(filepath)){
            try ( BufferedReader br = new BufferedReader(new FileReader(filepath))){
                
                //parse header 
                parseHeader(machine, br);

                if (validStart()){

                    // parse text records
                    
                    parseText(machine, br);


                }else{
                    throw new IOException("[ERROR] Invalid information in Header record. (Bad Address)");
                }
                

                //Everything should be set up so We can now set the PC

                //TODO make sure that the start addr is LEGAL
                machine.pc = new Word1(startAddr);
                

            } catch( IOException e){
                System.err.println("[ERROR] Loader Error in Opening File: " + e.getMessage());
                e.printStackTrace();
            }
        }else{

            System.err.println("[ERROR] Illegal Character in object file");
            System.exit(1);
        }
    }

    public boolean checkForIllegalCharacters(String filePath) {
        try {
            // Define legal characters
            String legalChars = " 0123456789_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\n\r";
            //TODO add alphabet in lowercase
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int c;
            
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                // Check if the character is not in the string of legal characters
                if (legalChars.indexOf(character) == -1) {
                    System.out.println("Illegal character found: " + character);
                    reader.close();
                    return false; // Illegal character found
                }
            }
            reader.close();
            return true; // No illegal characters found
        } catch (IOException e) {
            System.err.println("[ERROR] Error while reading filePath in checkForIllegalCharacters()");
            e.printStackTrace();
            return false; // Return false or handle the error appropriately
        }
    }
    
    private void parseText(Machine1 m, BufferedReader br) throws IOException{

        String line;

        while ((line = br.readLine()) != null) {
            
            if(line.startsWith("T")){
                int address = Integer.parseInt(line.substring(1,5), 16);
                int contents = Integer.parseInt(line.substring(5,9),16);
                
                Word addressWord = new Word1(address);
                Word contentsWord = new Word1(contents);

                //Now try and insert into memory

                //But only if the address is in bounds
                //TODO check that this is the correct bound
                //i just removed the = sign
                //
                if (address >= initLoadAddr && address <= initLoadAddr + programLength-1){
                    m.memory.setWord(addressWord, contentsWord);
                } else {
                    throw new IndexOutOfBoundsException("[ERROR] Tried to Load memory that is out of bounds"); 
                }


            } else if (line.startsWith("E")) {
                //Handle end record
                
                startAddr = Integer.parseInt(line.substring(1,5), 16);

                if (startAddr >= initLoadAddr && startAddr <= initLoadAddr + programLength-1){
                    //do nothing
                } else{
                    throw new IndexOutOfBoundsException("[ERROR] Tried to Load memory that is out of bounds");
                }

                
            }
        }
    }

    private boolean validStart(){
        //TODO fix the last if segment memMax- init should not throw error when on last address
        if ( initLoadAddr >= 0 && initLoadAddr < 65536 && 65536 - initLoadAddr >= programLength){
            return true;
        }
        return false;
    }


    private void parseHeader(Machine1 m, BufferedReader br) throws IOException{

        String headerLine = br.readLine();

        if(headerLine != null && headerLine.startsWith("H")){

            name = headerLine.substring(1,7);

            initLoadAddr = Integer.parseInt(headerLine.substring(7,11), 16);

            programLength = Integer.parseInt(headerLine.substring(11,15), 16);
            
        }else{
            throw new IOException("[ERROR] Invalid header format or empty file.");
        }
    }

}
