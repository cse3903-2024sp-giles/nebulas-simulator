package nebulas.simulator;

/**
 * Machine - Contains the current state of the machine.
 *
 *<p>
 *This contains all parts of the machine that should be simulated. Memory, PC, CPU, and both CCR registers and General Registers.
 *This should be treated like a state machine. It doesn't have methods to do any of the simulation, only to get and set its parts.
 *<p>
 **/
public interface Machine{

    enum MODE{
        QUIET,
        STEP,
        TRACE
    }
}
