import java.util.Scanner;
import java.io.*;
import java.net.*;

public class GameClient
{
    public static void main(String[] args) throws IOException 
    {
        try 
        {
            Socket GameSocket = new Socket("127.0.0.1", 4444); 		 //The IP address of the server and port number
            System.out.println("Connection established");	   		 //Let the user know connection has been made
            Scanner skIn = new Scanner(GameSocket.getInputStream()); //This is the input from the socket
            
            PrintWriter skOut = new PrintWriter(GameSocket.getOutputStream(),true); //Output from the socket
            Scanner kbdIn = new Scanner(System.in); 				 //Sets the keyboard as input for user
            System.out.println("Welcome to the game server!");
            System.out.println("What is your name?");				 //Prompt for username
            String username = kbdIn.nextLine();						 //username is equal to user input
            String serverResp = "";
            System.out.println("Hello " + username + ", I'm thinking of a number between 0 and 100. Can you" +
            		" guess what it is?");
            System.out.println("Type 'quit' to exit");
            
                   
            while (true) 
            {
                String userInput = kbdIn.nextLine(); 		//Get input from the keyboard
                skOut.println(userInput); 					//Send user input to the socket
                serverResp = skIn.nextLine(); 				//Response from socket
                System.out.println(serverResp); 			//Print server response to screen
                
                if (serverResp.equals("Thank you for playing."))
                    break; 									//Close the connection to server
            }
            System.out.println("Thank you for playing.");
            //Close all connections and sockets to server
            skOut.close();
            kbdIn.close();
            skIn.close();
            GameSocket.close();
        } 
        catch (ConnectException e) //Will throw error if there is no connection to the server
        {
            System.err.println("Could not connect. Ensure server is running.");
            System.exit(1);
        } 
        catch (IOException e) 	   //Will throw error if there is no input
        {
            System.err.println("Couldn't get I/O for connection");
            System.exit(1);
        }
    }
}