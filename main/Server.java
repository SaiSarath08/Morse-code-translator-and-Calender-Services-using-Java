package main;

import java.io.*;
import java.net.*;
import java.util.*;
import main.services.*;

public class Server {
	private static final int port = 7777;
	
	public static void main(String[] args) throws IOException{
		try(ServerSocket ss = new ServerSocket(port)){
			System.out.println("Server running on port : " + port);
			
			while(true)
			{
				Socket socket = ss.accept();
				new Thread(new ClientHandler(socket)).start();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	static class ClientHandler implements Runnable{
		private Socket socket;
		private CalendarService calendar;
		
		public ClientHandler(Socket socket){
			this.socket = socket;
		}
		
		
		public void run() {
			try( BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			   ){
				   out.println("Connection has establihed! Enter your name : ");
				   String ClientName = in.readLine();
				   out.println("Hi " + ClientName + "!" + "\n" + "These are the available servies : " );
				   out.println("1.Morsecode Encoder" + "\n" + "2.Morsecode Decoder" + "\n" + "3.Calendar Services" + "\n" + "Type 1 or 2 or 3 to request service or 'bye' to exit : " + "\n");
				   out.flush();
				   
				   String command;
				   while((command = in.readLine())!= null)
				   {
					   if(command.equals("1"))
					   {
						   out.println("Enter your text to encode : " + "\n");
						   String text = in.readLine();
						   out.println("Encoded Morse Text : " + MorseCode.morseEncoder(text));
					   }
					   if(command.equals("2"))
					   {
						   out.println("Enter the morse text (with spaces for each letter) : " + "\n");
						   String morse = in.readLine();
						   out.println("Decoded Morse Text : " + MorseCode.morseDecoder(morse));
					   }
					   if(command.equals("3"))
					   {
						   out.println("1.Day Predictor" + "\n" + "2.Next 3 Same Calendar Years" + "\n" + "3.Check LeapYear" + "\n" + "4.Main Menu" + "\n" + "Type 1 or 2 or 3 or 4 : " + "\n");
						   String command1;
						   while((command1 = in.readLine())!= null)
						   {
							   if(command1.equals("1"))
							   {
								   out.println("Enter Date in (DD/MM/YYYY) format : " + "\n");
								   String date = in.readLine();
								   out.println(date + " is " + calendar.dateToDay(date));
							   }
							   if(command1.equals("2"))
							   {
								   out.println("Enter Year only : " + "\n");
								   int year = Integer.parseInt(in.readLine());
								   int A1 = calendar.SameCalendar(year);
								   int A2 = calendar.SameCalendar(A1);
						           int A3 = calendar.SameCalendar(A2);
								   out.println("Next three consecutive same Calendar years are " + A1 + ", " + A2 + ", " + A3);
							   }
							   if(command1.equals("3"))
							   {
								   out.println("Enter year only : " + "\n");
								   int year = Integer.parseInt(in.readLine());
								   String result = calendar.IsLeapYear(year);
								   if(result == "yes")
								   {
									   out.println(year + " is a leap year" + "\n");
								   }
								   else if(result == "no") { out.println(year + " is not a leap year"); }
							   }
							   if(command1.equals("4"))
							   {
								   break;
							   }
							   out.println("1.Day Predictor" + "\n" + "2.Next 3 Same Calendar Years" + "\n" + "3.Check LeapYear" + "\n" + "4.Main Menu" + "\n" + "Type 1 or 2 or 3 or 4 : " + "\n");
							   out.flush();
						   }
					   }
					   if(command.equals("bye"))
					   {
						   out.println("Disconnecting from Server... \n");
						   break;
					   }
					   out.println("1.Morsecode Encoder" + "\n" + "2.Morsecode Decoder" + "\n" + "3.Calendar Services" + "\n" + "Type 1 or 2 or 3 to request service or 'bye' to exit : " + "\n");
					   out.flush();
				   }
			   }
			   catch(IOException e)
			   {
				   e.printStackTrace();
			   }
		}
	}
}
								   
			   
		
		