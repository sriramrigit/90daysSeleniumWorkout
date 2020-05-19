package ninetydaysworkout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DuplicateFinderCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input="When life gives you lemons, make lemonade";
		Set<Character> li=new TreeSet<Character>();
		for(int i=0;i<input.length();i++)
		{
			for(int j=i+1;j<input.length();j++)
			{
				if(input.charAt(i)==input.charAt(j))
				{
					li.add(input.charAt(i));
				}
			}
			
		}
		
		System.out.println(li);
	}

}
