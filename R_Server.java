//
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class R_Server {
	public static void main(String[] args)throws FileNotFoundException
	{   
		try {
			int RealServerPort = 35981;
			ServerSocket serverSocket = new ServerSocket(RealServerPort);
			while(true) { 
				System.out.println("Waiting for client on port " + RealServerPort);
                Socket RealServer = serverSocket.accept();// Listens for a connection to be made and accepts it.
				System.out.println("Just connected to " + RealServer.getRemoteSocketAddress()); 
                PrintWriter toPrxyServer = 
					new PrintWriter(RealServer.getOutputStream(),true);// writing to proxy server
				BufferedReader fromPrxyServer =
					new BufferedReader(
							new InputStreamReader(RealServer.getInputStream()));// reading from proxy server
				String line = fromPrxyServer.readLine();//reading domain from proxy server
				System.out.println("real Server received: " + line);
		        SocketAddress inet =RealServer.getLocalSocketAddress();
		        		  System.out.println("IP address of the machine is"+inet);// IP address of the machine
		        		  list li=new list();
		        		  String url=li.check(line);
		        		  toPrxyServer.println(url); //sending the modified url to the proxy server
		        		  
		        	  
		        	  
				
				
			}
		}
		catch(UnknownHostException ex) {
			ex.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
