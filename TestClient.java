import java.net.*;
import java.io.*;
import java.util.*;

public class TestClient 
{

	private static InetAddress loop;
	public static void main(String[] args) 
	{
		Scanner Scanner = new Scanner(System.in);
		OSock OSock = new OSock();
		OSock.set_isPlayerOne(false);
		InetAddress loop = InetAddress.getLoopbackAddress();
		
		OSock.set_ip(loop);
		OSock.connect();
		
		while(true)
		{
			System.out.println("Enter your message for the server");
			String message = Scanner.nextLine();
			OSock.writeSock(message);
		}
		
	}

}
