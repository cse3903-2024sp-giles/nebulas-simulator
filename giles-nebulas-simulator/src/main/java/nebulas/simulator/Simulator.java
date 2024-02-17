package nebulas.simulator;


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

    public Simulator(String filepath, Machine.MODE M, int runTime){
        
       runningTime = runTime;
       machine = new Machine1(M);  
       filePath = filepath; 


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
        

        Interpreter interpreter = new Interpreter(machine);


        boolean continueExecution = true;
        
        long startsTime = System.nanoTime();

        while(continueExecution){
            continueExecution = interpreter.step();
            

            if (System.nanoTime() - startsTime > runningTime){
                continueExecution = false;
            }
            
        }

    }
}
