package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import AuthenticationConstants.ACs;	// Authentication Constants

public class ServerClass {
	
	// Client
	public static final String AUTHENTICATIONMSG = "Hello SecStore, prove your ID";
	public static final String REQUESTSIGNEDCERT = "Give me your signed Certificate";
	public static final String TERMINATEMSG = "Bye";
	public static final String CLIENTID = "I am client";
	public static final String CLIENTPUBLICKEY = "Client Public key";
	
	// Server
	public static final String SERVERID = "I am SecStore";
	public static final String SIGNEDCERT = "1234567890";
	public static final String REQUESTCLIENTPUBLICKEY = "Send me your public key";	
	
	
	private static boolean terminateConnection(PrintWriter out){
		out.println(ACs.TERMINATEMSG);
		return false;
	}
	
	private static boolean authenticationProtocol(BufferedReader in, PrintWriter out) throws IOException{
		
		System.out.println("Starting authentication protocol");
		
		if(!(in.readLine()).equals(ACs.AUTHENTICATIONMSG)){
			terminateConnection(out);
		}
		out.println(ACs.SERVERID);
		if(!(in.readLine().equals(ACs.REQUESTSIGNEDCERT))){
			terminateConnection(out);
		}
		out.println(ACs.SIGNEDCERT);
		
		String message = in.readLine();	// Reads in the clientsID using client's private key
		
		out.println(ACs.REQUESTCLIENTPUBLICKEY);
		
		if(!(in.readLine().equals(ACs.CLIENTPUBLICKEY))){
			terminateConnection(out);
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		
		/*
		 * Possible IPs in school : 
		 * 			202.94.70.51
		 * 			103.24.77.51
		 * 
		 * 
		 */
		
		//String hostName = args[0];
		//int portNum = Integer.parseInt(args[1]);
		
		int portNum = 7777;	// socket address
		String hostName = "NicG";
		ServerSocket serverSocket;
		Socket clientSocket;
		
		serverSocket = new ServerSocket(portNum);
		
		System.out.println("Accepting client connections now ...");
		clientSocket = serverSocket.accept();
		System.out.println("Client connection established!");
		
		BufferedReader in = new BufferedReader(
								new InputStreamReader(
										clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
		boolean proceed = authenticationProtocol(in,out);
	
		
		
		
		
		
	}
}

class OpenConnections implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
