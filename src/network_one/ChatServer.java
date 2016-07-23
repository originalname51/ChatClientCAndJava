package network_one;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
private	  ServerSocket chatServer;
private         Socket client;
private    PrintWriter out;
private BufferedReader in;
private BufferedReader consoleMessage;
private final int    MESSAGE_LENGTH = 500;
private final int	 CHAR_OFFSET    = 100;

public	ChatServer(int portnumber)
		{
		try {
			this.chatServer = new ServerSocket(portnumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Critical Error. Socket Failed");
			e.printStackTrace();
		}		
	}

public void acceptConnection()
{		
	try {
		this.client 		= this.chatServer.accept();
		this.out			= new PrintWriter(this.client.getOutputStream(),true);
		this.in				= new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.consoleMessage = new BufferedReader(new InputStreamReader(System.in));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Critical Error. Accept Connection Failed");
		e.printStackTrace();
	}

}
//http://stackoverflow.com/questions/4644415/java-how-to-get-input-from-system-console
public boolean sendMessage()
{
	String sendme;
	try {
		sendme = consoleMessage.readLine();
		out.println(Integer.toString(sendme.length() +CHAR_OFFSET+1));
		out.flush();
		out.println(sendme);
		return _checkQuit(sendme);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
	}
	
	return false;
}

public boolean recieveMessage()
{
	String howLongMessageIs;
	String outputMessage;
	char [] message = new char[MESSAGE_LENGTH];
	int CharsToRead = 0;

	try {
	      howLongMessageIs = in.readLine();
	      CharsToRead      = Integer.parseInt(howLongMessageIs.trim());
	      CharsToRead     -= CHAR_OFFSET;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("FAILED IN RECIEVE MESSAGE");
			e.printStackTrace();
		}			
	try {
		if(in.read(message,0,CharsToRead-1) == -1)
			System.out.println("Critical Error. File not read correctly.");
		outputMessage = String.valueOf(message);
		System.out.println(outputMessage.trim());
		
		return _checkQuit(outputMessage);
		
	} catch (IOException e) {
		System.out.println("FAILED IN GETTING MESSAGE FROM SENDER! CRITICAL ERROR");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

private boolean _checkQuit(String check)
{
	return (check.trim().equals("\\quit")) ? false : true;	
}
public void shutDown()
{
	try {
		this.in.close();
		this.out.close();
		this.client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
