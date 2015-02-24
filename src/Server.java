import java.net.ServerSocket;

public class Server
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket svrsk = new ServerSocket(4444);		//Create new server socket, port 4444
		System.out.println("Welcome to the server!");
		System.out.println("");
		boolean itsOk = true;								//Set as true on start, go to while loop
		
		while (itsOk)
		{
			new ServerThread(svrsk.accept()).start();		//Create a new instance of ServerThread with each new connection
		}
		
		svrsk.close();
		System.out.println("All done");
	}
}