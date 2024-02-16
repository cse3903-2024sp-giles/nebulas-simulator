package nebulas.simulator;

import nebulas.simulator.Machine.MODE;

/**
 *Simulator - The class that sets up and runs the implementation *
 *<p>
 *Constructs the machine and loader and then runs the interpreter on the state until complete
 *<p>
 **/
public class Simulator{
    
    Machine1 machine;

    String filePath;

    public Simulator(String filepath, Machine.MODE M){
       machine = new Machine1(M);  
       filePath = filepath; 


       Loader1 loadr = new Loader1();

       
       try {
           loadr.loadMachine(machine, filePath);
       } catch (Exception e) {
           
        System.out.println("Error in loading machine: + " + e.getMessage());
        System.exit(1);
       }
       
    }
    


    /**
     *Runs the sim once it is constructed  */
    public void run(){
        

        Interpreter interpreter = new Interpreter(machine);


        boolean continueExecution = true;

        while(continueExecution){
            continueExecution = interpreter.step();
            
        }

    }
}
