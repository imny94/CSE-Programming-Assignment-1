import java.io.BufferedReader;
import AuthenticationConstants.ACs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CP1Client {

	public static void main(String[] args) throws IOException, InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		System.out.println("trying to connect");
		String hostName = "10.12.21.29";
		int portNumber = 7777;
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress(hostName, portNumber);
		echoSocket.connect(sockaddr, 8080);
		System.out.println("connected");
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		String initialMessage = ACs.AUTHENTICATIONMSG;
		out.println(initialMessage);
		out.flush();
		String serverInitialReply = in.readLine();
		System.out.println("gave me secret message");
		String serverSecondMessage = ACs.REQUESTSIGNEDCERT;
		out.println(serverSecondMessage);
		out.flush();
		String signedCertificate = in.readLine();
		System.out.println("gave me signed certificate");
		//extract public key from signed certificate
		
		
		//use public key to decrypt serverInitialReply
		
		
		//if serverInitialReply is correct, then proceed to give my encrypted client ID
		if (!signedCertificate.equals(ACs.SIGNEDCERT) || !serverInitialReply.equals(ACs.SERVERID)){
			out.println(ACs.TERMINATEMSG);
			out.flush();
			return;
		} else {
			out.println(ACs.CLIENTID);
			out.flush();
		}
		System.out.println("decryption of secret message is complete");
		
		//proceed to send my public key
		String serverThirdMessage = in.readLine();
		if (!serverThirdMessage.equals(ACs.REQUESTCLIENTPUBLICKEY)){
			out.println(ACs.TERMINATEMSG);
			out.flush();	
		} else {
			out.println(ACs.CLIENTPUBLICKEY);
			out.flush();
		}
		System.out.println("sent my public key");
		
		//if pass, then do handshake to send files
		String serverFourthMessage = in.readLine();
		if (serverFourthMessage.equals(ACs.SERVERREADYTORECEIVE)){
			//proceed
			System.out.println("everything works, proceed to shake hand");
		} else {
			System.out.println("didnt work");
		}
		
		
		
		
	}
	
	public static PublicKey ExtractPublicKeyFromSignedCertificate(String signedCertificate) throws FileNotFoundException, CertificateException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException{
		//creating X509 certificate object
		InputStream fis = new FileInputStream("/Users/G/Documents/50.005 CSE/NS Programming Assignment/CA(1).crt");
		
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate CAcert = (X509Certificate)cf.generateCertificate(fis);
				
		//extract public key from the certificate 
		PublicKey CAkey = CAcert.getPublicKey();
		/*
		Certificate servertCert;
		//check validity and verify signed certificate
		serverCert.checkValidity();
		serverCert.verify(CAkey);	
		*/
		
		return CAkey;
		
	}
	
	public static String DecryptInitialReply(String secretMessage, PublicKey pk){
		
		return "";
	}

}
