# User Guide

This is a guide on how to properly use the nebulas-simulator, which is an implementation of the specification "SP24-COLDX Machine"

## Table of Contents

- [Getting Started](/#getting-started)
  - [Requirements](/#requirements)
  - [Quick Start](/#quick-start)
- [Running the Program](/#running-the-program)
  - [Command-line Options](/#command-line-options)
- [Errors and Error Messages](/#errors-and-error-messages)

## Getting Started

Please ensure that the requirements are met on your machine before running.

### Requirements

- Maven
  - <https://maven.apache.org/download.cgi>
- Java 21
  - <https://jdk.java.net/21/>

### Quick Start

1. Ensure that Java 21 and Maven are installed on your system.
2. From the project directory `giles-nebulas-simulator`, compile the program using Maven:

    ```console
    mvn package
    ```

3. Run the program using the generated JAR file in the `target` directory:

    ```console
    java -jar target/giles-nebulas-simulator-1.0-SNAPSHOT.jar [options] -l 20 -f [input_file]
    ```
    
    This will run the machine in `Quiet` mode with an upper time limit of 20 seconds using the input object file `[input_file]`

    Omitting any mode setting options will run the machine in `Quiet` mode. 

    Check [command-line options](/#command-line-options) to understand how to customize the program's behavior according to your needs.

## Running the Program

To run nebulas-simulator, use the following command:

```console
java -jar target/giles-nebulas-simulator-1.0-SNAPSHOT.jar [options] -l 20 -f [input_file]
```

### Command-line Options

#### Required arguments
- `-f [input_file]` : Provide the input file to be processed by the program.

- `-l [time limit]` : Provide the upper limit in seconds that the machine is allowed to run. 


#### Running mode options
The following option set the running mode. If you set more than one of these the most verbose will be used. 
- `[options]` 
  - `--step` : Will load file as normal, but prompts user for input before executing each step
  - `--quiet` : [Default] Runs without any extra diagnostics or user input
  - `--trace` : Prompts user for input before executing step but also prints diagnostic information to console

## Input file


## Troubleshooting Tips
- **Read the Error Message**: Read what is printed to the screen. Often times this is enough to tell what was wrong or at the very least where the bug came from.
- **Double check your arguments**: Make sure that the arguments provided make sense for what is running. Some arguments are required for the program to run. Reference the [Running mode options](#running-mode-options) section to ensure correct usage. 
- **Check input file**: This is a machine that runs an input file as a program so bugs could also exist in the object file. As long as the input file is valid, it will not. The machine does not do checks to ensure that there aren't logical errors while loading or running. As long as the input file is valid format, it will be run.
- **Check the Error Messages Section**: If the printed message doesn't help, take a look at the possible solutions in the below [Errors and Error Messages](#errors-and-error-messages). 
- **Still Stuck**: Reference the programmers guide or javadocs. 


## Errors and Error Messages

This section outlines some common errors, what they mean, and possible solutions.


### Invalid time limit
- **Message**: `The time limit must be an integer.`
- **Description**: This occurs when the argument to the `-l` argument is not able to be parse to an integer. 
- **Resolution**: Please ensure that you are using the arguments correctly and passing a sensible time limit for your program to run.

### Example Error
- **Message**:
- **Description**:
- **Resolution**:
