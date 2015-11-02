import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Scanner;
public class ProxyCache 
{
	  static Multimap<String, String> cacheMap= ArrayListMultimap.create(); 
	  
	 
	  //ProxyCache pc=new ProxyCache();   
public static String create(String ans,int maxsize) throws IOException
 {
	 File txt=new File("M:/CacheAddress.txt");
	Scanner scanner=new Scanner(txt);
	System.out.println("elements in the cache map are");
	 while(scanner.hasNext()){
		 String no=scanner.next();
		 String[] sa=no.split("\\_", 2);
		cacheMap.put(sa[0], sa[1]);// creating multimap
	     
		}
	 /// displaying content of multimap
	 Set<String> keys = cacheMap.keySet();
		for (String key : keys) {
			System.out.println("Key = " + key);
			System.out.println("Values = " + cacheMap.get(key));
			}
	 scanner.close();
    // checking the size	
	 if(cacheMap.size()<maxsize)
	 {
	   String lol=check(ans);
	   return lol;
	   
	 }
    else 
    { 
    	System.out.println("Size exceeded so deleting the least accessed");
	    File temp1 = new File("M:/temp1.txt");
	 BufferedReader br1 = new BufferedReader(new FileReader(txt));
	 PrintWriter pw1 = new PrintWriter(new FileWriter(temp1));
	 String shr;
	 int i=0;
	 // removing the first line as it is least accessed
	 while((shr = br1.readLine()) != null) 
	   {
		i++;
	    if(i==1)
	       continue;
	     pw1.write(shr + System.getProperty("line.separator"));
	     
	    }
	 pw1.close(); 
	 br1.close(); 
	BufferedReader br2 = new BufferedReader(new FileReader(temp1));
	 PrintWriter pw2 = new PrintWriter(new FileWriter(txt));
	 String currentLine;
	    while((currentLine = br2.readLine()) != null) 
	     {
		     pw2.write(currentLine + System.getProperty("line.separator"));
		     
		 }
	    pw2.close(); 
		 br2.close(); 
		 cacheMap.clear();
		 System.out.println("revised elements in the cache are");
		 Scanner sc=new Scanner(txt);
		 String no;
		 while(sc.hasNext()){
			 no=sc.next();
		
			 String[] sa=no.split("\\_", 2);
			cacheMap.put(sa[0], sa[1]);
			
			}
		 sc.close();
		 Set<String> kys = cacheMap.keySet();
			for (String key : kys) {
				System.out.println("Key = " + key);
				System.out.println("Values = " + cacheMap.get(key));
				}

     String rfl=check(ans);
     return rfl;
 }
 }

public static String check(String ans) throws IOException
{
	

	String[] array=ans.split("\\.",2);
	 String ext=array[1];
	 String domain=array[0];
    File txt=new File("M:/CacheAddress.txt");
   if(cacheMap.containsKey(ext))
	 {
		 Collection<String> grp = cacheMap.get(ext);
			String[] arry = grp.toArray(new String[grp.size()]);
			int flag=0;
			for(int i=0;i<arry.length;i++)
			{
				String[] app=arry[i].split("\\&&",2);
			
				if(domain.equals(app[0]))
				{ 
					String ap=app[0]+"&&"+LastChecked();
					System.out.println(ap);
				    cacheMap.remove(ext,arry[i]);
					cacheMap.put(ext,ap);
					File temp = new File("M:/temp.txt");
					BufferedReader br = new BufferedReader(new FileReader(txt));
				    PrintWriter pw = new PrintWriter(new FileWriter(temp));
				    String line = null;
				    String lineToRemove = ext+"_"+arry[i];
				    String newLine;
                  while((newLine = br.readLine()) != null) 
                  {
				     
				     String triLine = newLine.trim();
				     if(triLine.equals(lineToRemove)) continue;
				     pw.write(newLine + System.getProperty("line.separator"));
				     
				 }
				 String nw=ext+"_"+ap;
				
				 	pw.write(nw);
				 	pw.close(); 
				 	br.close();
				 	flag=1;
				}
				else
				{
					
					continue;
				}
				
				
			}
			if(flag==0)
			{
				System.out.println("here1");
				 String rfl=connection(ext,domain);
				 return rfl;
			}
			System.out.println("updates version is");
			Set<String> kys = cacheMap.keySet();
			for (String key : kys) {
				System.out.println("Key = " + key);
				System.out.println("Values = " + cacheMap.get(key));
				}
	 
	     File temp = new File("M:/temp.txt");
	     BufferedReader br = new BufferedReader(new FileReader(temp));
		    PrintWriter pw = new PrintWriter(new FileWriter(txt));
		    String newLine;
		    while((newLine = br.readLine()) != null) 
		    {
			  pw.write(newLine + System.getProperty("line.separator"));
			     
			 }
		     pw.close(); 
			 br.close(); 
			 String ln="Information received from Proxy cache";
			 return ln;
	
}
	 else
	 {
		 System.out.println("here");
		 String rfl=connection(ext,domain);
		 return rfl;
	 }
}
	 public static String connection(String ext,String domain) throws IOException
	 {
		 File txt=new File("M:/CacheAddress.txt");
			   System.out.println("have to connect to real server");
   		        FileWriter fstream = new FileWriter(txt,true);
	            BufferedWriter fbw = new BufferedWriter(fstream);
	            int RealServerPort = 35981;
	        	byte[] ipAddr = new byte[]{(byte)127, (byte)0, 0, 1}; //IP address of real server machine
	        	InetAddress host = InetAddress.getByAddress(ipAddr); 
	        		//String host = "localhost";
	       		   // InetAddress address = InetAddress.getByName(host);
	        		System.out.println("Connecting to real server on port " + RealServerPort); 
	                Socket socket = new Socket(host,RealServerPort); // to connect to real server
	        		System.out.println("Connected to " + socket.getRemoteSocketAddress()); 
	        		PrintWriter toRealServer = new PrintWriter(socket.getOutputStream(),true);// sending message to real server
	        		String ans=domain+"."+ext; 
	          		toRealServer.println(ans);//new domain to Real server
	          		 //to read from Real server
	                  BufferedReader fromRealServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        		String url = fromRealServer.readLine();// information received from Real Server
	        		 // adding the received domain in to poxy cache
	        		String api;
	        		if(url.equals("not found in real server as well")){
	        			api = "Not found in real server";
	        		}
	        		else if(url.equals("extension does not exist")){
	        			api = "wrong input";
	        		}
	        		
	        		else{
	        		System.out.println("Proxy Server received: " + url ); 
	        		
	        		api=domain+"&&"+LastChecked();
	        		fbw.newLine();
		            fbw.write(ext+"_");
		            fbw.write(api);
	        		}
	        		fbw.close();
	        		fstream.close();
	        		fromRealServer.close();
	        		socket.close();
	        		return url;
	        		
	 }
	 
public static String LastChecked()
{
	java.util.Date date= new java.util.Date();
   	Timestamp a= new Timestamp(date.getTime());
   	String p=a.toString();
   	String time=p.replace(" ", "/");
   	return time;
	 
	 
 }
 
}