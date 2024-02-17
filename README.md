# User Guide

This is a guide on how to properly use the nebulas-simulator, which is an implementation of the specification "SP24-COLDX Machine"

## Table of Contents

- [Getting Started](#getting-started)
  - [Requirements](#requirements)
  - [Quick Start](#quick-start)
- [Running the Program](#running-the-program)
  - [Command-line Options](#command-line-options)
- [Input File Format](#input-file-format)
- [Errors and Error Messages](#errors-and-error-messages)

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

    Check [command-line options](#command-line-options) to understand how to customize the program's behavior according to your needs.

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

## Input File Format
A valid object file must adhere to a specific standard. Any deviation from this could and will likely cause an error. 


A valid input file consists of the following:
- **Input File Structure**:
  - **A Header Record**:
    - `record position 1`: H
    - `record positions 2-7`: a 6 character segment name
    - `record positions 8-11`: a 4 Hex character value denoting the initial program load address
    - `record positions 12-15`: a 4 Hex character value denoting the length of the segment
  - **Text Records**:
    - `record position 1`: T
    - `record positions 2-5`: a 4 Hex character address at which the information is to be stored
    - `record positions 6-9`: Initial contents of that address, as a 4 Hex character string
  - **End Record**:
    - `record position 1`: E
    - `record positions 2-5`: a 4 Hex character address at which execution is to begin


## Troubleshooting Tips
- **Read the Error Message**: Read what is printed to the screen. Often times this is enough to tell what was wrong or at the very least where the bug came from.
- **Double check your arguments**: Make sure that the arguments provided make sense for what is running. Some arguments are required for the program to run. Reference the [Running mode options](#running-mode-options) section to ensure correct usage. 
- **Check input file**: This is a machine that runs an input file as a program so bugs could also exist in the object file. As long as the input file is valid, it will not. The machine does not do checks to ensure that there aren't logical errors while loading or running. As long as the input file is valid format, it will be run.
- **Check the Error Messages Section**: If the printed message doesn't help, take a look at the possible solutions in the below [Errors and Error Messages](#errors-and-error-messages). 
- **Still Stuck**: Reference the programmers guide or javadocs. 


## Errors and Error Messages

This section outlines some common errors, what they mean, and possible solutions.

### Invalid time limit
- **Message**: `[ERROR] The time limit must be an integer.`
- **Description**: This occurs when the argument to the `-l` argument is not able to be parse to an integer. 
- **Resolution**: Please ensure that you are using the arguments correctly and passing a sensible time limit for your program to run.

### IO Error
- **Message**: `[ERROR] Error while reading filePath in checkForIllegalCharacters()`
- **Description**: This occurs when there is an IO error in reading the file.
- **Resolution**: Check to make sure that the file path is valid and not empty. 

### Illegal Characters Error
- **Message**: `[ERROR] Illegal Character in object file`
- **Description**: There are illegal characters in the object file.
- **Resolution**: Ensure that the input file is valid and is accessible to the program.

### Header Error
- **Message**:`[ERROR] Invalid information in Header record. (Bad Address)`
- **Description**: The header record in the input file might be present but is not correct or contains an invalid load address.
- **Resolution**: Ensure that the header record is correctly formatted and contains a valid address and length.

### Memory Error
- **Message**:`[ERROR] Tried to Load memory that is out of bounds`
- **Description**: Occurs when a text record tries to load a memory address that is out of bounds.
- **Resolution**: Ensure that each of the text records have valid addresses and aren't trying to write outside of address space claimed in the header record.

### Invalid Format Error
- **Message**: `[ERROR] Invalid header format or empty file.`
- **Description**: Occurs when the file can be read but the header record may not exist or be invalid. 
- **Resolution**: Ensure that the header record adheres to the specification. 

### Loading Error
- **Message**: `[ERROR] Error in loading the machine: `
- **Description**: Occurs when the machine cannot be loaded.
- **Resolution**: This is a general loading error. To understand what specifically is wrong, reference errors above. It is unlikely that this error is printed alone. 

### Memory Error
- **Message**: `[ERROR] Cannot read address out of bounds`
- **Description**: Occurs when an address is trying to be read from that does not exist.
- **Resolution**: Ensure that your program does not try and read from inaccessible memory. 

### Memory Error
- **Message**: `[ERROR] Cannot write address out of bounds`
- **Description**: Occurs when an address is trying to written that does not exist.
- **Resolution**: Ensure that your program does not try and write from inaccessible memory. 

### Register Error
- **Message**: `[ERROR] Tried to set an invalid register`
- **Description**: Occurs when an instruction tries to write to a register that does not exist.
- **Resolution**: Ensure that your program is correct and does not try to set registers that aren't 0-7

### Register Error
- **Message**: `[ERROR] Tried to get an invalid register`
- **Description**: Occurs when an instruction tries to read a register that does not exist.
- **Resolution**: Ensure that your program is correct and does not try to get registers that aren't 0-7

### Word Setting Error
- **Message**: `[ERROR] Error in position. (Greater than 15 or less than 0)`
- **Description**: Occurs when something tries to set or get a bit that doesn't exist.
- **Resolution**: Ensure that your program is correct. This is unlikely to be bug related to the input file. It is more likely a bug. Record reproducible steps and make an issue on Github.

### Word Setting Error
- **Message**: `[ERROR] String does not contain only 1s and 0s.`
- **Description**: Occurs when something tries to construct a word object with an invalid input string.
- **Resolution**: Ensure that your program is correct. This is unlikely to be bug related to the input file. It is more likely a bug. Record reproducible steps and make an issue on Github.

### Instruction Error
- **Message**: `[ERROR] Word in CPU is not an instruction`
- **Description**: Occurs when the instruction that is trying to be ran is not a valid operation code. 
- **Resolution**: Ensure that your program is correct and does not try to run words from memory that are not instructions. This is likely an issue with the file loaded. 

### Interpreter Error
- **Message**: `[Interpreter][ERROR] [" + vector + "] is not a TRAP vector`
- **Description**: Occurs when a trap instruction is called but the vector provided is not recognized from the instruction set. 
- **Resolution**: Ensure that your program is correct and does not try to run words from memory that are not instructions. This is likely an issue with the file loaded. 
