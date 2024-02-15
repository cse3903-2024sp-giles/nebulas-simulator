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
    java -jar target/giles-nebulas-simulator-1.0-SNAPSHOT.jar [options]
    ```

    Check [command-line options](/#command-line-options) to understand how to customize the program's behavior according to your needs.

## Running the Program

To run nebulas-simulator, use the following command syntax:

```console
java -jar target/giles-nebulas-simulator-1.0-SNAPSHOT.jar [input_file]
```

### Command-line Options
<!-- TODO: Add command-line options as new ones are added -->
- `[input_file]`: Provide the input file to be processed by the program.

## Errors and Error Messages
