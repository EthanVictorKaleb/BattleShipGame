import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer 
{
	static int portNum = 49153;
	static InetAddress ip = InetAddress.getLoopbackAddress();
	static PrintWriter out;
	static BufferedReader in;

	public static void main(String[] args) 
	{
		
		
		
		OSocklocal OSocklocal = new OSocklocal();
		OSocklocal.set_isPlayerOne(true);
		OSocklocal.set_ip(InetAddress.getLoopbackAddress());
		
		System.out.println("Connecting...");
		OSocklocal.connect();
		System.out.println("Connected.");
		
		while(true)
		{
			String echo = OSocklocal.readSock();
			if(echo != null)
			{
				System.out.println(echo);
				
			}
		}

	}//end main
	
	
}//end class
