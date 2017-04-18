package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import AuthenticationConstants.ACs;	// Authentication Constants

public class ServerClass {
	
	private static boolean authenticationProtocol(BufferedReader in, PrintWriter out) throws IOException{
		
		if(!(in.readLine()).equals(ACs.AUTHENTICATIONMSG)){
			out.println(ACs.TERMINATEMSG);
			return false;
		}
		out.println(ACs.SERVERID);
		if(!(in.readLine().equals(ACs.REQUESTSIGNEDCERT))){
			out.println(ACs.TERMINATEMSG);
			return false;
		}
		out.println(ACs.SIGNEDCERT);
		
		if(!(in.readLine().equals(ACs.CLIENTID))){
			out.println(ACs.TERMINATEMSG);
			return false;
		}
		
		out.println();
		
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		
		//String hostName = args[0];
		//int portNum = Integer.parseInt(args[1]);
		int portNum = 7777;	// socket address
		String hostName = "NicG";
		ServerSocket serverSocket;
		Socket clientSocket;
		
		serverSocket = new ServerSocket(portNum);
		clientSocket = serverSocket.accept();
		
		BufferedReader in = new BufferedReader(
								new InputStreamReader(
										clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
		
	
		
		
		
		
		
	}
}

class OpenConnections implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
