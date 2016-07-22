package network_one;

public class MainClass {
	
	/*
	 * Class Project
	 * 
	 * */

	public static void main(String[] args) {
		ChatServer chatserver = new ChatServer(Integer.parseInt(args[0]));
		chatserver.acceptConnection();
	
		while(true)
		{
		chatserver.sendMessage();
		chatserver.recieveMessage();
		}
	}

}

