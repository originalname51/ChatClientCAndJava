package network_one;

public class MainClass {
	/*
	 * Class Project
	 * 
	 */
	public static void main(String[] args) {
		ChatServer chatserver = new ChatServer(Integer.parseInt(args[0]));
		while (true) {
			chatserver.acceptConnection();
			while (true) {	
				if (!(chatserver.sendMessage())) {
					chatserver.shutDown();
					break;
				}	
				if (!(chatserver.recieveMessage())) {
					chatserver.shutDown();
					break;		
				}
			}
		}
	}
}
