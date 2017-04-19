# CSE-Programming-Assignment-2

## Project Description:
This Project was completed whilst taking the Course 50.005 Computer Systems Engineering at Singapore University of Technology and Design. The aim of this project is to implement a secure file transfer protocol using, first, asymmetric key cryptography and second, symmetric key cryptography and then compare the runtime of the two.

### Collaborators:
Nicholas Yeow Teng Mun (1001490)

Yin Ji Sheng (1001670)

## Specifications of the Protocols:
The are two main objective for this program, first is to authenticate the server and the client, and make sure each of them is the true and intended sender and receiver. The second objective is to send an encrypted file securely from the client to the authenticated server. The first is achieved using the Authentication Protocol (AP) while the second is achieved using the Confidentiality Protocol (CP).  

To achieve the first objective, this protocol makes use of asymmetric key cryptography to authenticate each other's identity. A nonce request is first sent from the client to the server, and then the server will encrypt the nonce message with its own private key and send it back to the client. A nonce is used to prevent a playback attack by a malicious eavesdropper. The client then request for the server's signed certificate and the server will send it to the client accordingly. The client extracts the server's public key using the signed certificate and that public key is used to decrypt the encrypted nonce message. If the decrypted nonce message is the same as the original nonce message generated, then one can be sure that the server is the true intended receiver for the client's requests. Once this is achieved, the program goes on to ensure the client is not any malicious sender but a true intended sender as well. The same process is used where the server will send a nonce message to the client for the client to authenticate as the true intended sender. Below is a chart to describe the AP. 

In this assignment, the second objective is achieved using two different cryptography methods: asymmetric key cryptography using RSA algorithm, and symmetric key cryptography using AES algorithm. In this submission, the asymmetric key method is shortnamed CP1 while the symmetric key cryptography is shortnamed CP2. Their respective runtimes are computed and plotted in a graph to compare their speeds. The outcomes can be found at the last section of this page. 

## How to Compile the program on Eclipse:
To compile the program on Eclipse, one must have two different machines with active Internet connection. Before running the program, one must change the I.P address of which the client connects to. Simply look for hostName at line 49 of the respective client programs and change the I.P address to which server is currently listening on. 
```
String hostName = "10.12.21.29";
```
One must also modify the run configurations of the client code. All 3 smallFile.txt, medianFile.txt and largeFile.txt file paths must be included as arguments to be parsed into the main method. 

To run the CP1 codes first, one must run the CP1Server.java first on one computer before the other computer can run the CP1Client.java. If the client computer fails to connect to the server within 8080ms, the program will throw an timeout error. Both programs should run automatically until the end without errors. The CP1Server.java will print the total time taken for the file to be encrypted on the client, sent to the server, and then finally decrypted by the server. The server will receive the encrypted file, decrypt it and save it on its own local computer.

Repeat the same process for CP2 codes. 
## Outcomes and Results:
![alt text][graph1]

[graph1]:https://github.com/imny94/CSE-Programming-Assignments/CSE Programming Assignment 2/CPComputationTime.png "Logo Title Text 1"
