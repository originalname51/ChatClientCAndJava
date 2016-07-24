package network_one;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
private	  ServerSocket chatServer;
private         Socket client;
private    PrintWriter out;
private BufferedReader in;
private BufferedReader consoleMessage;
private final int  	   MESSAGE_LENGTH = 500;
private final int	   CHAR_OFFSET    = 100;
/*
 * Chat Server Start Up - takes and starts the chat server.
 * Creates a reader for console messages.
 * http://download.java.net/jdk7/archive/b123/docs/api/java/net/ServerSocket.html#ServerSocket(int, int, java.net.InetAddress)
 * used for figuring out how to bind an IP address to a socket.
 * http://www.java-samples.com/showtutorial.php?tutorialid=399 used for figuring out
 * the InetAddress constructor. (It didn't have a default constructor or need one).
 * */
public	ChatServer(int portnumber)
		{
		try {
			this.chatServer = new ServerSocket(portnumber,50,InetAddress.getLocalHost());
			this.consoleMessage = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			System.out.println("Critical Error. Socket Failed.");
			e.printStackTrace();
		}		
	}
/*
 * This will accept a connection. 
 * It will accept a connection from a client
 * It will create an output (using a print writer)
 * It will also create an input.
 * http://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
 * */
public void acceptConnection()
{		
	try {
		this.client 		= this.chatServer.accept();
		this.out			= new PrintWriter(this.client.getOutputStream(),true);
		this.in				= new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	} catch (IOException e) {
		shutDown(); //if it fails print a function specific message and shut down the connection.
		System.out.println("Critical Error. Accept Connection Failed");
		e.printStackTrace();
	}

}
//http://stackoverflow.com/questions/4644415/java-how-to-get-input-from-system-console
//http://stackoverflow.com/questions/2340106/what-is-the-purpose-of-flush-in-java-streams
public boolean sendMessage()
{
	String sendme;
	try {
		sendme = consoleMessage.readLine();
		out.println(Integer.toString(sendme.length() + CHAR_OFFSET+1));
		out.flush();
		out.println(sendme);
		return _checkQuit(sendme);
	} catch (IOException e) {
		shutDown();
		e.printStackTrace();
	}
	return false;
}
/*
 * //http://stackoverflow.com/questions/8694984/remove-part-of-string
 * 
 * This will receive a message. It first takes a newline character value from the client.
 * It will parse this as an integer value. It will then 
 * 
 * Protocol is client sends how long a message is, and then the message itself.
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * */
public boolean recieveMessage()
{
	String howLongMessageIs;
	String outputMessage;
	char [] message = new char[MESSAGE_LENGTH];
	int CharsToRead = 0;

	// First read how large the message is. Note it will always be the CHAR_OFFSET more in order to have a constant
	// integer size value (i.e. 105 instead of '5', 125 instead of 25).
	// This allows for values up to 900 characters long, 400 more than the specifications require.
	try {
	      howLongMessageIs = in.readLine();
	      CharsToRead      = Integer.parseInt(howLongMessageIs.trim());
	      CharsToRead     -= CHAR_OFFSET;
		} catch (IOException e) {
			shutDown();
			System.out.println("FAILED IN RECIEVE MESSAGE");
			e.printStackTrace();
		}	
	
	/*
	 * This will read in the full message from the client. 
	 * It will then display it, add a new line and check to see if the client wants to \quit.
	 * */
	try {
		if(in.read(message,0,CharsToRead) == -1)
		System.out.println("Critical Error. File not read correctly.");
		outputMessage = String.valueOf(message);
		System.out.print(outputMessage.trim());	//this will print instead of println to preserve all sent newlines.
		System.out.print("\n");					//This will print a newline to separate server send values.

		//The +2 here is because ">" = 1 and there is always a space after it, (so substring (index+2...)).
		int index = outputMessage.indexOf(">");
		String checker = outputMessage.substring(index+2, outputMessage.length());
		return _checkQuit(checker);
		
	} catch (IOException e) {
		System.out.println("FAILED IN GETTING MESSAGE FROM SENDER! CRITICAL ERROR");
		shutDown();
		e.printStackTrace();
	}
	return false;
}
/*
 * Helper function. Checks to see if string is "\quit".
 * */
private boolean _checkQuit(String check)
{
	return (check.trim().equals("\\quit")) ? false : true;	
}

/*
 * This function will shut down the server.
 * */
public void shutDown()
{
	try {
		this.in.close();
		this.client.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	finally{
		this.out.close();
	}
}
	
}
