//import packages
import java.net.*;
import java.io.*;

//this class is to give networking capability to the battleship game
public class OSock 
{
	

	//variables
	private boolean isPlayerOne;
	private int portNum = 49152;
	private InetAddress ip;
	PrintWriter out;
	BufferedReader in;
	
	//accessors
	public boolean get_isPlayerOne()
	{
		if(isPlayerOne)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public int get_portNum()
	{
		return portNum;
	}
	
	public InetAddress get_ip()
	{
		return ip;
	}
	
	//mutators
	public void set_isPlayerOne(boolean set)
	{
		isPlayerOne = set;
	}
	
	public void set_portNum(int set)
	{
		portNum = set;
	}
	
	public void set_ip(InetAddress set)
	{
		ip = set;
	}
	
	//this creates the socket to connect to the other player
	//you must set ip equal to the other player's ip before you call this method
	public void connect()
	{
		//if the player selects player one they will be the server
		if(isPlayerOne)
		{
			try
			(
					//create a socket if port is available
					ServerSocket SSocket = new ServerSocket(portNum);
			)
			{
				//accept connection from client and bind received data to a stream
				Socket clientSocket = SSocket.accept();
				out = new PrintWriter(clientSocket.getOutputStream(), true);                   
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			}
			catch(IOException e)
			{
				if(portNum > 65535)
				{
					//print error message if no ports are available
					System.out.println(e.getMessage());
					System.out.print("(The program could not find a port to connect to)");
				}
				else
				{
					//pick a new port number and retry
					portNum = portNum + 1;
					connect();
				}
			}
			finally
			{
				//no code needed here
			}
			
		}//end if(isPlayerOne)
		
		//if the player selects player two they will be the client
		else if(!isPlayerOne)
		{
			try
			(
				//try and create a socket if port number is available
				Socket CSocket = new Socket(ip, portNum);	
			)
			{
				out = new PrintWriter(CSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(CSocket.getInputStream()));
			}
			catch(IOException s)
			{
				if(portNum > 65535)
				{
					//print error message if no ports are available
					System.out.println(s.getMessage());
					System.out.print("(The program could not find a port to connect to)");
				}
				else
				{
					//pick a new port number and retry
					portNum = portNum + 1;
					connect();
				}
			}
			finally
			{
				//no code needed here
			}
		}//end else if
	
	}//end connect() method
	
	//this reads what the other player sends you, returns it as a string
	public String readSock()
	{
		String sockOut = "";
		//handle IOException
		try
		{
			if(in.readLine() != null)
			{
				sockOut = in.readLine();
			}
		}
		catch(IOException e)
		{
			//dont know what to do with the exception
			System.out.println("in.readLine() threw an IOException and I really \ndon't know what to do with it or why it \n would do that but eclipse said to handle \n it so I put this here. Good luck. -Ethan");
			System.out.println(e);
		}
		return sockOut;
	}//end readSock()
	
	//this sends data to the other player, you pass it as the variable send
	public void writeSock(String send)
	{
		out.print(send);
	}//end writeSock()

}//end OSock


