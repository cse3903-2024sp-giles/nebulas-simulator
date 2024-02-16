package nebulas.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loader1 {

    private static int initLoadAddr;
    private static int programLength;
    private static int startAddr;
    private static String name;
    
    public void loadMachine(Machine1 machine, String filepath) throws IOException{

        if (checkForIllegalCharacters(filepath)){
            try ( BufferedReader br = new BufferedReader(new FileReader(filepath))){
                
                //parse header 
                parseHeader(machine, br);

                if (validStart()){

                    // parse text records
                    
                    parseText(machine, br);


                }else{
                    throw new IOException("Invalid information in Header record");
                }
                

                //Everything should be set up so We can now set the PC

                machine.pc = new Word1(startAddr);
                

            } catch( IOException e){
                System.err.println("Loader Error in Opening File: " + e.getMessage());
                e.printStackTrace();
            }
        }else{

            System.err.println("Illegal Character in object file");
            System.exit(1);
        }
    }

    public boolean checkForIllegalCharacters(String filePath) {
        try {
            // Define legal characters
            String legalChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ\n\r"; // Include newline and carriage return if necessary
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
                if (address >= initLoadAddr && address <= initLoadAddr + programLength){
                    m.memory.setWord(addressWord, contentsWord);
                } else {
                    throw new IndexOutOfBoundsException("Tried to Load memory that is out of bounds"); 
                }


            } else if (line.startsWith("E")) {
                //Handle end record
                
                startAddr = Integer.parseInt(line.substring(1,5), 16);
                
            }
        }
    }

    public static boolean validStart(){
        if ( initLoadAddr >= 0 && initLoadAddr < 65536 && 65536 - initLoadAddr > programLength){
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
            throw new IOException("Invalid header format or empty file.");
        }
    }

}
