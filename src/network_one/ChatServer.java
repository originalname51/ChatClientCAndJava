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
private final int    LENGTH = 5;
private final int    MESSAGE_LENGTH = 500;
//private DataOutputStream output;

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
		this.client = this.chatServer.accept();
		this.out	= new PrintWriter(this.client.getOutputStream(),true);
//		this.output    = new DataOutputStream(this.client.getOutputStream());
		this.in		= new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Critical Error. Accept Connection Failed");
		e.printStackTrace();
	}

}

public void sendMessage()
{
	String sendme = "Hello C Client, this is Java Server!!!!!!!!";
	out.println(Integer.toString(sendme.length() +101));
	out.flush();
	out.println(sendme);
}

public void recieveMessage()
{
	String howLongMessageIs;
	String outputMessage;
	char [] message = new char[MESSAGE_LENGTH];
	int number = 0;
	
		try {
			howLongMessageIs = in.readLine();
			number = Integer.parseInt(howLongMessageIs.trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("FAILED IN RECIEVE MESSAGE");
			e.printStackTrace();
		}
	number -= 100;			
	try {
		if(in.read(message,0,number) == -1)
			System.out.println("Critical Error. File not read correctly.");
		outputMessage = String.valueOf(message);
		System.out.println(outputMessage);
		
	} catch (IOException e) {
		System.out.println("FAILED IN GETTING MESSAGE FROM SENDER! CRITICAL ERROR");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
