// Client Side
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;
public class Client {
public static void main(String args[]) throws FileNotFoundException{	
	
	try {
		int serverPort = 35591;
		byte[] ipAddr = new byte[]{(byte)127, (byte)0, 0, 1}; //to connect to  different machine host
		InetAddress host = InetAddress.getByAddress(ipAddr); 
		//String hst = "localhost";
		 //InetAddress host  = InetAddress.getByName(hst); // to your own machine
		System.out.println("Connecting to server on port " + serverPort); 
        Socket socket = new Socket(host,serverPort); // connecting to the proxy server
		System.out.println("Just connected to " + socket.getRemoteSocketAddress()); 
        Scanner p=new Scanner(System.in);
          //OutputStream os = socket.getOutputStream();
          //OutputStreamWriter osw = new OutputStreamWriter(os);
          //BufferedWriter bw = new BufferedWriter(osw);
          System.out.println("enter the domain name");
          String msg=p.nextLine();
          PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true); ///writing to proxy  server
  		  toServer.println(msg);
        //to read from proxy server
          BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));// receiving message from proxy server
		String line = fromServer.readLine();
		System.out.println("Client received: " + line );
		fromServer.close();
		socket.close();
	}
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }
	
  

}