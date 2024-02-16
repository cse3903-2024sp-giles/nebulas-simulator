package nebulas.simulator;

import org.apache.commons.cli.*;

import nebulas.simulator.Machine.MODE;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Handle CLI inputs 
        Options options = new Options();
        
        Option quiet = new Option("q", "quiet", false, "Run in quiet mode");
        quiet.setRequired(false);
        options.addOption(quiet);
        Option trace = new Option("t", "trace", false, "Run in trace mode");
        trace.setRequired(false);
        options.addOption(trace);
        Option step = new Option("s", "step", false, "Run in step mode");
        step.setRequired(false);
        options.addOption(step);
        

        //user defined time limit 
        Option time = new Option("l", "limit", true, "The upper time limit in seconds the sim is allowed to run");
        time.setRequired(true);
        options.addOption(time);
        
        

        //file input
        Option file =  new Option("f", "file", true, "File path to object file");
        

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;

        try{
            cmd = parser.parse(options, args);
        }catch (ParseException e){
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            

            System.exit(1);
            return;
        }
        
        Machine.MODE runningModeInput = Machine.MODE.QUIET;

        if(cmd.hasOption("s")){
            runningModeInput = Machine.MODE.STEP;
        }

        if(cmd.hasOption("t")){
            runningModeInput = Machine.MODE.TRACE;
        }

        
        String inputFile = cmd.getOptionValue("f");
        String runningTimeString = cmd.getOptionValue("l");
        int runTimeInt = 0; 
        try{
            runTimeInt = Integer.parseInt(runningTimeString);
        }catch (NumberFormatException exception){
            System.err.println("The time limit must be an integer.");
            System.exit(1);
        }

        Simulator sim = new Simulator(inputFile, runningModeInput, runTimeInt);

        sim.run();
    }
}
