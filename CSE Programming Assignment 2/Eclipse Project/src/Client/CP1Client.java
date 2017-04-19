package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;

import AuthenticationConstants.ACs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

public class CP1Client {

	public static void main(String[] args) throws IOException, InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		System.out.println("trying to connect");
		String hostName = "127.0.0.1";//"10.12.21.29";
		int portNumber = 7777;
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress(hostName, portNumber);
		echoSocket.connect(sockaddr, 8080);
		System.out.println("connected");
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		//DataInputStream inData = new DataInputStream((echoSocket.getInputStream()));
		//DataInputStream in = new DataInputStream(new InputStreamReader(echoSocket.getInputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		//InputStream in = echoSocket.getInputStream();
		//OutputStream out = echoSocket.getOutputStream();
		
		//send message and receive encrypted message
		String initialMessage = ACs.AUTHENTICATIONMSG;
		out.println(initialMessage);
		out.flush();
		String serverInitialReply = in.readLine();
		System.out.println("gave me secret message");
		
		//send request for cert and receive signed cert
		String secondMessage = ACs.REQUESTSIGNEDCERT;
		out.println(secondMessage);
		out.flush();
		String sizeInString = in.readLine();
		int certificateSize = Integer.parseInt(sizeInString);
		byte[] signedCertificate = new byte[certificateSize];
		signedCertificate = DatatypeConverter.parseBase64Binary(in.readLine());
		System.out.println("gave me signed certificate");
		
		//extract public key from signed certificate
		//creating X509 certificate object
		FileOutputStream fileOutput = new FileOutputStream("CA.crt");
        fileOutput.write(signedCertificate, 0, certificateSize);
        FileInputStream certFileInput = new FileInputStream("CA.crt");
         
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate CAcert = (X509Certificate)cf.generateCertificate(certFileInput);
						
		//extract public key from the certificate 
		PublicKey CAkey = CAcert.getPublicKey();				
		CAcert.checkValidity();
		CAcert.verify(CAkey);
		
		//use public key to decrypt serverInitialReply
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, CAkey);
		byte[] decryptedBytes = cipher.doFinal(DatatypeConverter.parseBase64Binary(serverInitialReply));
		String decryptedMessage = DatatypeConverter.printBase64Binary(decryptedBytes);
		System.out.println("message decrypted" + decryptedMessage);
		
		//if serverInitialReply is correct, then proceed to give my encrypted client ID
		if (!decryptedMessage.equals(ACs.SERVERID)){
			out.println(ACs.TERMINATEMSG);
			out.close();
			in.close();
			echoSocket.close();
			return;
		} 
		System.out.println("successfully authenticated the server");
		//encrypt clientId 
		
		//get public key 
		Path keyPath = Paths.get("D:\\Backup\\SUTD\\ISTD\\Computer Systems Engineering\\CSE-Programming-Assignments\\CSE Programming Assignment 2\\publicServer.der");
		byte[] publicKeyInBytes = Files.readAllBytes(keyPath);
		
		out.println(ACs.CLIENTID);
		out.flush();
		
		
		/*
		if (!signedCertificate.equals(ACs.SIGNEDCERT) || !serverInitialReply.equals(ACs.SERVERID)){
			out.println(ACs.TERMINATEMSG);
			out.flush();
			return;
		} else {
			out.println(ACs.CLIENTID);
			out.flush();
		}
		System.out.println("decryption of secret message is complete");
		*/
		
		
		
		//proceed to send my public key
		//int serverThirdMessage = in.read();
		/*
		if (!serverThirdMessage.equals(ACs.REQUESTCLIENTPUBLICKEY)){
			out.print(ACs.TERMINATEMSG);
			out.flush();	
		} else {
			out.println(ACs.CLIENTPUBLICKEY);
			out.flush();
		}
		
		System.out.println("sent my public key");
		*/
		
		
		
		//if pass, then do handshake to send files
		//int serverFourthMessage = in.read();
		/*
		if (serverFourthMessage.equals(ACs.SERVERREADYTORECEIVE)){
			//proceed
			System.out.println("everything works, proceed to shake hand");
		} else {
			System.out.println("didnt work");
		}
		*/
	}


}
