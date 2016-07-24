package network_one;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClass {
	/*
	 * Class Project
	 * This will run in an infinite loop.
	 * It will create a chatserver and then accept connections allowing for back and forth client/server communication.
	 */
	public static void main(String[] args) {
	
		ChatServer chatserver = new ChatServer(Integer.parseInt(args[0]));
		while (true) {
			try {
				System.out.println("ChatServer awaiting connection on IP address:" + InetAddress.getLocalHost()+ " on port " + args[0]);
			} catch (UnknownHostException e) {
				System.out.println("LocalHostNotFound!");
				e.printStackTrace();
			}
			chatserver.acceptConnection();
			while (true) {	
				if (!(chatserver.recieveMessage())) {
					chatserver.shutDown();
					break;		
				}
				if (!(chatserver.sendMessage())) {
					chatserver.shutDown();
					break;
				}	
			}
			System.out.println("Connection Closed.");
		}
	}
}
