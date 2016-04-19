/*
 * Programa Servidor TCP
 * 
 */
 
 import java.io.*;
 import java.net.*;
 import java.util.Scanner;

public class TCPServer{
	
	public static void main (String args[]) throws IOException{
		
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream));
			Scanner inFromClient = new Scanner(connectionSocket.getInputStream());		
			
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.nextLine();
			capitalizedSentence = clientSentence.toUpperCase() + "\n";
			outToClient.writeBytes(capitalizedSentence);
			
		}
		
		
	}
}

