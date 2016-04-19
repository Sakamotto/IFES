/*
 * Programa Cliente TCP
 * 
 */
 
 import java.io.*;
 import java.net.*;
 import java.util.Scanner;


public class TCPClient{
	
	public static void main (String args[]) throws IOException{
		
		String sentence;
		String modifiedSentence;
		Scanner inFromUser = new Scanner(System.in);
		
		Socket clientSocket = new Socket("localhost", 6789);
		PrintStream outToServer = new PrintStream(clientSocket.getOutputStream());
		
		//BufferedReader inFromServer = new BufferedReader(new InputStramReader(clientSocket.getInputStream()));
		Scanner inFromServer = new Scanner(clientSocket.getInputStream());
		
		sentence = inFromUser.nextLine();		
		outToServer.writeBytes(sentence + "\n");
		modifiedSentence = inFromServer.nextLine();
		System.out.println("FROM SERVER: "+ modifiedSentence);
		clientSocket.close();
		
	}
}

