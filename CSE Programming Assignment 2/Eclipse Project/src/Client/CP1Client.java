import java.io.BufferedReader;
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
		String hostName = "10.12.21.29";
		int portNumber = 7777;
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress(hostName, portNumber);
		echoSocket.connect(sockaddr, 8080);
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		String initialMessage = "Hello SecStore, prove your ID";
		out.println(initialMessage);
		out.flush();
		String serverInitialReply = in.readLine();
		String serverSecondMessage = "Give me your signed Certificate";
		out.println(serverSecondMessage);
		out.flush();
		String signedCertificate = in.readLine();
		if (!signedCertificate.equals("1234567890") || !initialMessage.equals("I am SecStore")){
			out.println("Bye");
			out.flush();
			return;
		} else {
			out.println("I am client");
			out.flush();
		}
		String serverThirdMessage = in.readLine();
		if (!serverThirdMessage.equals("")){
			//
		} else {
			out.println("Sending public key");
			out.flush();
		}
		
		/*
		//extract public key from signed certificate
		PublicKey pk = ExtractPublicKeyFromSignedCertificate(signedCertificate);
		
		//use the public key to decrypt serverInitialReply
		String decryptedMessage = DecryptInitialReply(serverInitialReply, pk);
		if (!decryptedMessage.equals("I am SecStore")){
			//abort mission
		}
		//send i want to upload ...
		 * 
		 */
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
