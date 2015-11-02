// proxy Server Side
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Server { 
	public static void main(String[] args)throws  IOException
	{

	try 
	{
		int serverPort = 35591;
		ServerSocket serverSocket = new ServerSocket(serverPort);
	
		while(true) 
		{
			System.out.println("Connecting to client  " + serverPort); 
            Socket server = serverSocket.accept();// Listens for a connection to be made and accepts it
			System.out.println("Connected to " + server.getRemoteSocketAddress()); 
            PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);// writing to client
		    BufferedReader fromClient =new BufferedReader(new InputStreamReader(server.getInputStream()));//reading from client
			String line = fromClient.readLine();// the message from client
			System.out.println("ProxyServer received: " + line);
			ProxyCache pc=new ProxyCache();
 			String answer=pc.create(line, 5);
   		    toClient.println("Msg from proxy server with IP address" + server.getLocalSocketAddress() +" "+"is"+" "+answer);
			
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
	

