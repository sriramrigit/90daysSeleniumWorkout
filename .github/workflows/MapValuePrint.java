package ninetydaysworkout;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MapValuePrint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map< String,Integer> mp=new TreeMap<String, Integer>();
		mp.put("A",1);
		mp.put("B",2);
		mp.put("C",3);
		mp.put("D",4);
		mp.put("E",5);
		
		for(Entry<String, Integer> eachentry :mp.entrySet())
		{
			System.out.println(eachentry);
		}
		
		//approach 2
		Set< Map.Entry< String,Integer> > st = mp.entrySet();    
		  
	       for (Map.Entry< String,Integer> me:st) 
	       { 
	           System.out.print(me.getKey()+"->"); 
	           System.out.println(me.getValue()); 
	       } 
		
		//approach 3
	    Map<String,Integer> lh=new LinkedHashMap<String,Integer>();
	    lh.putAll(mp);
	     Set entrySet=lh.entrySet();
	    Iterator it = entrySet.iterator();
	    System.out.println("LinkedHashMap entries : ");
	    
	    while(it.hasNext())
	     
	      System.out.println(it.next());
	         
	      }

	}


