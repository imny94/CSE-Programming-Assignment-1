# CSE-Programming-Assignment-1

## Project Description:
This Project was completed whilst taking the Course 50.005 Computer Systems Engineering at Singapore
University of Technology and Design. The aim of this project is to illustrate the concept of
dependencies and to understand how we can handle such dependencies using the relevant 
data structures and control flow.

### Collaborators:
Nicholas Yeow Teng Mun (1001490)

Yin Ji Sheng ()

## Purpose of the program:
This program will control the execution of different processes based on the control and data 
dependencies described in a given text file describing a Directed Acyclic Graph. From the input 
given to this program, the program will create and maintain the relevant Directed Acyclic Graph 
modelling the various dependencies that exist. Using this Graph, the program will then determine
the sequence in which the different programs will run, controlling when each process will run,
ensuring that the dependencies on the Graph are not violated, and all processes are only run when
the relevant dependencies are satisfied. 

## How to Compile the program:
When Executing from the command line, type in the following if program is being executed for the first time :
```java
javac ProcessManagement.java
```

After the program is successfully compiled, enter the following to run the program :
```java
java ProcessManagement
```

Before executing the program, ensure the relevant dependency graph is stored in the same location as this program.


## What will the program do:
