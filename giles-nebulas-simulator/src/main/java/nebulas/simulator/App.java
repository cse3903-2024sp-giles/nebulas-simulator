package nebulas.simulator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        Simulator sim = new Simulator(args[0].toString(), Machine.MODE.QUIET);

        sim.run();
    }
}
