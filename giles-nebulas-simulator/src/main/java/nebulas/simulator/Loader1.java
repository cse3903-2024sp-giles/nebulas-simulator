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

        try ( BufferedReader br = new BufferedReader(new FileReader(filepath))){
            
            //parse header 
            parseHeader(machine, br);

            if (validStart()){

                // parse text records
                // 


            }else{
                throw new IOException("Invalid information in Header record");
            }
            

        } catch( IOException e){
            System.err.println("Loader Error in Opening File: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void parseText(Machine1 m, BufferedReader br) throws IOException{

        String line;

        while ((line = br.readLine()) != null) {
            
            if(line.startsWith("T")){
                int address = Integer.parseInt(line.substring(1,5), 16);
                int contents = Integer.parseInt(line.substring(5,9),16);
                
                Word addressWord = new Word1();


            }
        }
    }

    private boolean validStart(){
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
