package nebulas.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *Simulator - The class that sets up and runs the implementation *
 *<p>
 *Constructs the machine and loader and then runs the interpreter on the state until complete
 *<p>
 **/
public class Simulator{
    
    Machine1 machine;

    String filePath;
    
    private int runningTime;
    
    private Machine.MODE simRunMode;

    public Simulator(String filepath, Machine.MODE M, int runTime){
        
       runningTime = runTime;
       machine = new Machine1(M);  
       filePath = filepath; 
       simRunMode = M;


       Loader1 loadr = new Loader1();

       
       try {
           loadr.loadMachine(machine, filePath);
       } catch (Exception e) {
           
        System.err.println("[ERROR] Error in loading machine: + " + e.getMessage());
        System.exit(1);
       }
       
    }
    


    /**
     *Runs the sim once it is constructed  */
    public void run(){
        
        //reader for user step
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
       //If trace or step print machine information 
        Interpreter interpreter = new Interpreter(machine);
        
        if(simRunMode != Machine.MODE.QUIET){
            interpreter.printState();
        }

        boolean continueExecution = true;
        
        long startsTime = System.nanoTime();

        while(continueExecution){
            continueExecution = interpreter.step();
            

            if ((System.nanoTime() - startsTime > runningTime) && simRunMode == Machine.MODE.QUIET){
                continueExecution = false;
            }
            

            //if trace or step mode, then wait for user input
            if (simRunMode == Machine.MODE.STEP) {
                waitForUser(reader);
            }

            
            
        }

         if(simRunMode != Machine.MODE.QUIET){
            interpreter.printState();
        }
       


    }

    private static void waitForUser(BufferedReader reader){
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[ERROR Reading user input]");
        }
    }
}
