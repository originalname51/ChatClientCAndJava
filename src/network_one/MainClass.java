package network_one;

public class MainClass {
	/*
	 * Class Project
	 * This will run in an infinite loop.
	 * It will create a chatserver and then accept connections allowing for back and forth client/server communication.
	 */
	public static void main(String[] args) {
		ChatServer chatserver = new ChatServer(Integer.parseInt(args[0]));
		while (true) {
			System.out.println("ChatServer awaiting connection on port " + args[0]);
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
		}
	}
}
