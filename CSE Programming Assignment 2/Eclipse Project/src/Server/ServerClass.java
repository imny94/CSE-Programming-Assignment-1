package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import AuthenticationConstants.ACs;	// Authentication Constants

public class ServerClass {
	
	private static boolean sendMsg(PrintWriter out,byte[] msg){
		out.println(msg);
		out.flush();
		return true;
	}
	
	private static boolean terminateConnection(PrintWriter out){
		out.println(ACs.TERMINATEMSG);
		return false;
	}
	
	private static boolean authenticateClient(String encryptedID, String clientPBKey){
		//TODO: apply public key on sncryptedID and compare to expected value
		if(encryptedID.equals(ACs.CLIENTID)){
			return true;
		}
		
		return false;
	}
	
	private static boolean authenticationProtocol(BufferedReader in, PrintWriter out) throws IOException{
		
		System.out.println("Starting authentication protocol");
		
		if(!(in.readLine()).equals(ACs.AUTHENTICATIONMSG.getBytes() )){
			System.out.println("Authenticaion message error!");
			return terminateConnection(out);
		}
		
		sendMsg(out,ACs.SERVERID.getBytes());
		
		if(!(in.readLine().equals(ACs.REQUESTSIGNEDCERT.getBytes() ))){
			System.out.println("Request Signed Certificate Error!");
			return terminateConnection(out);
		}
		
		sendMsg(out,ACs.SIGNEDCERT.getBytes());
		
		// Reads in the clientsID encrypted with client's private key
		String clientID = in.readLine();	
		
		sendMsg(out,ACs.REQUESTCLIENTPUBLICKEY.getBytes());
		
		String clientPublicKey = in.readLine();
		
		if(!authenticateClient(clientID, clientPublicKey)){
			System.out.println("Client Authentication Error!");
			return terminateConnection(out);
		}
		
		sendMsg(out,ACs.SERVERREADYTORECEIVE.getBytes());
		
		System.out.println("Completed authentication protocol");
		
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		
		//String hostName = args[0];
		//int portNum = Integer.parseInt(args[1]);
		
		int portNum = 7777;	// socket address
		ServerSocket serverSocket;
		Socket clientSocket;
		
		serverSocket = new ServerSocket(portNum);
		
		System.out.println("Accepting client connections now ...");
		clientSocket = serverSocket.accept();
		System.out.println("Client connection established!");
		
		// in will receive input as byte[]
		BufferedReader in = new BufferedReader(
								new InputStreamReader(
										new DataInputStream(clientSocket.getInputStream())));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
		boolean proceed = authenticationProtocol(in,out);
		
		if(!proceed){
			System.out.println("Authentication protocol failed!");
		}
		
		System.out.println("Waiing for encrypted file from client");
		
		serverSocket.close();
		
		
		
		
	}
}

class OpenConnections implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
