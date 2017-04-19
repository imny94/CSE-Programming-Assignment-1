# CSE-Programming-Assignment-2

## Project Description:
This Project was completed whilst taking the Course 50.005 Computer Systems Engineering at Singapore University of Technology and Design. The aim of this project is to implement a secure file transfer protocol using two different types of cryptographic methods, asymmetric key cryptography and symmetric key cryptography. We will then compare the runtime of the two different cryptographic methods.

### Collaborators:
Nicholas Yeow Teng Mun (1001490)

Yin Ji Sheng (1001670)

## Specifications of the Protocols:
This program is split into two main parts, ensuring authentication and confidentality. The first part of the protocol will authenticate both the server to the client, and vice-versa. This will make sure both of them are the intended sender and recipient. After authentication, the next objective would be to securely transmit a file from the client to the authenticated server. This is done via encryption, where the client will send an encrypted file to the authenticated server. The outline for our Authentication Protocol (AP) and Confidentiality Protocol (CP) is outlined below. 

### Authentication Protocol (AP) 
Our Authentication Protocol utilises asymmetric key cryptography to authenticate the identity of both the server and the client. To prevent playback attacks, a nonce request is first sent from the client to the server, and then the server will encrypt the nonce message with its own private key before sending it back to the client. The client will then request for the server's signed certificate to decrypt the encrypted nonce reply from the server. If the decrypted reply from the server matches the nonce initially transmitted by the client to the server, the client can be assured of both the identity of the server, as affirmed by the trusted certification authority, and the absence of a playback attack. This procedure is then repeated by the server to authenticate the identity of the client as well. Below is a chart to describe the AP. 


### Confidentiality Protocol (CP)
After the server and client have successfully authenticated each others identity, we implement two different types of confidentiality protocols and compare the performance between the different protocols. The two protocols will be defined as CP1 and CP2.

#### CP1
CP1 implements asymmetric key cryptography using RSA.

#### CP2
CP2 implements symmetric key cryptography using AES.

To compare the performance between the 2 different confidentiality protocols,their respective runtimes are computed and plotted in a graph to compare their speeds. The outcomes can be found at the last section of this page.  

## How to Compile the program on Eclipse:
To compile the program on Eclipse, one must have two different machines with active Internet connection. Before running the program, one must change the I.P address of which the client connects to. Simply look for hostName at line 49 of the respective client programs and change the I.P address to which server is currently listening on. 
``` java
String hostName = "XX.XX.XX.XX";  // Enter the IP Address of the server here
```
One must also modify the run configurations of the client code. All 3 smallFile.txt, medianFile.txt and largeFile.txt file paths must be included as arguments to be parsed into the main method. 

To run the CP1 codes first, one must run the CP1Server.java first on one computer before the other computer can run the CP1Client.java. If the client computer fails to connect to the server within 8080ms, the program will throw an timeout error. Both programs should run automatically until the end without errors. The CP1Server.java will print the total time taken for the file to be encrypted on the client, sent to the server, and then finally decrypted by the server. The server will receive the encrypted file, decrypt it and save it on its own local computer.

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
