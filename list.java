import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
public class list {
	public String check(String ans) 
	{
		 
		// creating  multimap to store key and values
		Multimap<String, String> multiMap = ArrayListMultimap.create();
		// putting values into  .com
		multiMap.put("com", "google");
		multiMap.put("com", "linkedin");
		multiMap.put("com", "flipkart");
		multiMap.put("com", "bing");
		multiMap.put("com", "salom");
		multiMap.put("com", "gmail");
		multiMap.put("co.in", "gmail");
		// putting values into  .in
		multiMap.put("in", "ebay");
		multiMap.put("in", "google");
		multiMap.put("in", "amazon");
		// putting values into  .org
		multiMap.put("org", "wikipedia");
		multiMap.put("org", "w3");
		multiMap.put("org","mozilla");
		multiMap.put("org","apache");
		
		multiMap.put("net","slideshare");
		multiMap.put("net", "sourceforge");
		
		 String[] aray=ans.split("\\.",2);
		 String ext=aray[1];
		 String domain=aray[0];
	// get all the set of keys
		Set<String> keys = multiMap.keySet();
		if(multiMap.containsKey(ext))
		{
			Collection<String> sh = multiMap.get(ext);
			String[] array = sh.toArray(new String[sh.size()]);
			int flag=1;
			for(int i=0;i<array.length;i++){
				if(domain.equals(array[i])){
					System.out.println("it exists in real server");
					System.out.print(array[i]);
				flag=0;
				}
				else{
					continue;
				}
			}
			if(flag==1){
				String pn="not found in real server as well";
				return pn;
			}
			String ln="information received from real serevr";
			return ln;
			
		}
		else{
			String pn="extension does not exist";
			return pn;
		}
		
		
		 
		// iterate through the key set and display key and values
		/*for (String key : keys) {
		System.out.println("Key = " + key);
		System.out.println("Values = " + multiMap.get(key));
		}*/
		
		}
	
	}

		
