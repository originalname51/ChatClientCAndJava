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
		this.in		= new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Critical Error. Accept Connection Failed");
		e.printStackTrace();
	}
	System.out.println("We made it. Connected!!");
	

	try {
		String output = in.readLine();
		
		System.out.println("we made it " + output);
		String sendme = "ccc";
		out.println(sendme);

	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("failed!");
		e.printStackTrace();
	}
	
	
	
	
	
	
	
}



	
}
