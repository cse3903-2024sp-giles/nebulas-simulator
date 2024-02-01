package nebulas.simulator;

import java.nio.file.*;

/**
 *Loader - The Loader contains one public method that will Load and setup a machine based on a file path
 *
 *<p>
 *Loads the machine object and sets up for simulation
 *<p>
 **/
public interface Loader{
    //This should not be implemented in a seperate class object. The implementation should be here in the interface

    /**
     * Loads the machine based on the filepath provided
     * @param machine The machine object to be loaded
     * @param path The file path of the program to be loaded
     */
     static void loadMachine(Machine machine, Path path){

         //TODO
     }
}
