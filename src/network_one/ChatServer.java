package network_one;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
private	  ServerSocket chatServer;
private         Socket client;
private    PrintWriter out;
//private BufferedReader in;
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
//		this.in		= new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Critical Error. Accept Connection Failed");
		e.printStackTrace();
	}

}

public void sendMessage()
{
	String sendme = "Hello C Client, this is Java Server!!!!!!!!";
	out.println(Integer.toString(sendme.length() +100));
	out.flush();
	out.println(sendme);
}

	
}
