import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread
{
	private Socket skt = null;				//Initialise socket as null
	
	public ServerThread(Socket socket)
	{
		skt = socket;
	}
	
	public void run()
	{
		System.err.println("Client has connected successfully");	//Print to server screen
		
		String inputLine;			//This is the user input as a string
		int playerguess;			//This is the user input as an integer
		int counter = 0;			//The players guess count starts as 0
			
		int Random =(int) (Math.random() * 100); //Create a random number between 0 and 100
		
		try
		{
			Scanner in = new Scanner(skt.getInputStream());			
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			
			while (true)
			{
				inputLine = in.nextLine();					//Each new line is the users input
				playerguess = Integer.parseInt(inputLine);	//Convert the userinput to an integer
				
				if (playerguess == Random)					//If the playerguess equals the random number
				{
					out.println("Congratulations! You guessed correctly!"	//Print to user
				+ " You guessed the correct number in " + counter + " attempts. " +
						"Would you like to play again? y/n");
					
					if (inputLine.equalsIgnoreCase("y"))		//If the user want to replay and enters y
					{
						//Random =(int) (Math.random() * 100);
					}
					else if (inputLine.equalsIgnoreCase("n"))	//If the user wants to quit and enters n
					{
						out.println("Thank you for playing.");
						break;									//Break out of the While loop
					}
				}
				else if (playerguess < Random)					//If the player guess is below the random number
				{
					out.println(playerguess + "? that's too low!");
					counter ++;									//Add one to the counter
				}
				else if (playerguess > Random)					//If the player guess is above the random number
				{
					out.println(playerguess + "? that's too high!");
					counter ++;									//Add one to the counter
				}
				else if (inputLine.equalsIgnoreCase("quit"))	//If the player types quit, ignoring case
				{
					out.println("Thank you for playing.");
					break;										//Break out of the While loop
				}
				
			}
			//Close all connections
			out.close();			
			in.close();
			skt.close();
		}
		catch (Exception e)											//If the client has disconnected from the server
		{
			System.err.println("Client connection termintated");	//Print error message to server screen
		}
	}
}