package main;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
	public static void main (String[] args)throws IOException{
		//Connects to the server
		String host = "localhost";
		try(Socket s = new Socket(InetAddress.getByName(host),7777);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
			Scanner sc = new Scanner(System.in);) 
			{
				
		System.out.println(in.readLine()); //Prints Welcome message
		
		String name = sc.nextLine(); //enter the client name
		out.println(name); // writes client name to server
		
		System.out.println("Server Response : ");
		String response ;
		while((response = in.readLine()) != null && !response.isEmpty())
		{
			System.out.println(response);
		}
		 while (true) {

                String userInput = sc.nextLine(); //  Read user input

				out.println(userInput);	// Send input to server
				
                if (userInput.equalsIgnoreCase("bye")) {
					response = in.readLine();
					System.out.println(response);
                    break;
                }

                // Read and print the server's response
                while ((response = in.readLine()) != null && !response.isEmpty()) {
                    System.out.println("Server: " + response);
                }
            }
        } 
		catch(IOException e){
			e.printStackTrace();
		}
	}
}