# CSE-Programming-Assignment-2

## Project Description:
This Project was completed whilst taking the Course 50.005 Computer Systems Engineering at Singapore University of Technology and Design. The aim of this project is to implement a secure file transfer protocol using two different types of cryptographic methods, asymmetric key cryptography and symmetric key cryptography. We will then compare the runtime of the two different cryptographic methods.

### Collaborators:
Nicholas Yeow Teng Mun (1001490)

Yin Ji Sheng (1001670)

## Specifications of the Protocols:
This program is split into two main parts, ensuring authentication and confidentality. The first part of the protocol will authenticate both the server to the client, and vice-versa. This will make sure both of them are the intended sender and recipient. After authentication, the next objective would be to securely transmit a file from the client to the authenticated server. This is done via encryption, where the client will send an encrypted file to the authenticated server. The principle for our Authentication Protocol (AP) and Confidentiality Protocol (CP) is outlined below. 

### Authentication Protocol (AP) 
Our Authentication Protocol utilises asymmetric key cryptography to authenticate the identity of both the server and the client. To prevent playback attacks, a nonce request is first sent from the client to the server, and then the server will encrypt the nonce message with its own private key before sending it back to the client. The client will then request for the server's signed certificate to decrypt the encrypted nonce reply from the server. If the decrypted reply from the server matches the nonce initially transmitted by the client to the server, the client can be assured of both the identity of the server, as affirmed by the trusted certification authority, and the absence of a playback attack. This procedure is then repeated by the server to authenticate the identity of the client as well. Below is a chart to describe the AP. 

![alt text](https://github.com/imny94/CSE-Programming-Assignments/blob/master/CSE-Programming-Assignment-2/APFigure.001.jpeg "Logo Title Text 1")


### Confidentiality Protocol (CP)
After the server and client have successfully authenticated each others identity, we implement two different types of confidentiality protocols and compare the performance between the different protocols. The two protocols will be defined as CP1 and CP2.

#### CP1
CP1 implements asymmetric key cryptography using RSA.

#### CP2
CP2 implements symmetric key cryptography using AES.

To compare the performance between the 2 different confidentiality protocols,their respective runtimes are computed and plotted in a graph to compare their speeds. The outcomes can be found at the last section of this page.  

## How to Compile the program on Eclipse:
To compile the program on Eclipse, one should have two different machines with an active Internet connection. Before running the program, one must configure the I.P address to which the client will attempt to connect to the server. This parameters are parsed to the client and server programs via the command line. This can either be done in Eclipse or via the Command line. 

### Client Side configuration

The input to the run configurations should be in the following order: 
1) hostName 
2) portNumber 
3) path to smallFile.txt 
4) path to medianFile.txt 
5) path to largeFile.txt. 

Below is an example of how the run configuration should look like when running the client from Eclipse:
```
"10.12.21.29" "7777" "smallFile.txt" "medianFile.txt" "largeFile.txt"
```

### Server Side Configuration

The input to the run configurations should be in the following order: 
1) PortNumber to listen on 
2) Path to Servers private Key
3) Path to Servers Signed Certificate

Below is an example of how the run configuration should look like when running the client from Eclipse:
```
"10.12.21.29" "7777" "smallFile.txt" "medianFile.txt" "largeFile.txt"
```

### Compiling from the Command Line / Terminal

The Client and Server file can also be compiled from the command line using "javac" and "java" commands. This is not shown here as the recommended method to run the two files would be via Eclipse or other environments as the different java files are in different packages for better organisation, making compilation slightly more advanced.


To run the CP1 codes first, one should run the CP1Server.java first on one computer before the other computer can run the CP1Client.java. If the client computer fails to connect to the correct server within 8080ms, the program will throw an timeout error. Both programs should run automatically until the end without errors. The CP1Server.java will print the total time taken for the file to be encrypted on the client, sent to the server, and then finally decrypted by the server. The server will receive the encrypted file, decrypt it and save it on its own local computer.

Repeat the same process for CP2 codes. 
## Outcomes and Results:

### Timings for CP1
|File Size | Time take for decryption (ms) |
| ------------- |:-------------:|
|1408      |	101	|
|17408     |	583 |
|575488    |	16096	|
|1729536   |	44658	|
|3782144   |	112783 |
|13812864  |	381981 |


### Timings for CP2
|File Size | Time take for decryption (ms) |
| ------------- |:-------------:|
|1296      |	91	|
|17392     |	111|
|575376    |	203	|
|1729472   |	657|
|3782048   |	1332|
|13812800  |	4195|	


![alt text](https://github.com/imny94/CSE-Programming-Assignments/blob/master/CSE-Programming-Assignment-2/CPComputationTime.png "Logo Title Text 1")
