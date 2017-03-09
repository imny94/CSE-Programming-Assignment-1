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
This program has been made to be executed in the command line, and only on linux-based systems, as the commands
in the instruction set are linux commands.

When Executing from the command line, type in the following if program is being executed for the first time :
```java
javac ProcessManagement.java
```
This will compile the relevant program files required for this program to run.

After the program is successfully compiled, enter the following to run the program :
```java
java ProcessManagement instructionset.txt
```
Note that the instructionset.txt can be any other file name, as long as the file is in the right format. Also,
before executing the program, ensure the relevant dependency graph is stored in the same location as this program,
and in the right data format.

The text file should describe the individual nodes within the Graph, whereby it will include information
on the to be executed for each node, the dependencies of the process, as well as its input and output
files.

The text file is colon delimited and in the following format:
```
Command with arguments space delimited: All Dependencies space delimited : All Input Space Delimited : Output 
```
An Example would be:
```
grep 3:6:cat-out.txt:grep-out.txt
```
The ID of each node will be based on the order in which the nodes appears in the text file. A new line
represents a new node.

## What will the program do:

This program will read the graph representing the dependencies of various processes and, using that graph,
this program will then control how the processes are being executed based on the dependency requirements of 
each process. 

This is done by maintaining a data structure for each node/process in the tree, whereby each process will have
a record of the current status of itself, such as whether the process has already been executed, if it is 
ready to be executed, the parent dependencies it has, the child dependencies, and its input and output files. 
Utilising this data structure, the program will create a process graph, made up of the individual processes.

By iterating through the Process Graph, this program will then fork new processes from the Operating System 
whenever it encounters a process in the Process Graph that has not been executed, and is ready to be executed,
while holding back processes which has yet to be executed, but not ready to be executed, i.e. its dependencies
have yet to be met.

This will thus allow the program to only allow processes whose dependencies have been made to be executed.
