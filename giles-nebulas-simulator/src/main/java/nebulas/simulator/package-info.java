/**
 * This package provides an implementation of a virtual machine based on the ISA and program format provided in CSE 3903.
 *The project is divided into individual components representing what would be the hardware of the machine. Provided is a Machine class that
 *operates as a state machine which keeps the state of all the compenents. The Interpreter is called on by the simulator to parse and run a Machine once it is setup.
 *Before a machine can be run it must be loaded by passing it to the loader along with a file path to the program that is to be loaded.
 *@since 2/1/24
 *@author Team nebulas*/
package nebulas.simulator;
