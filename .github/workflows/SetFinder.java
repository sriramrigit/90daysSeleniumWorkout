package ninetydaysworkout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetFinder {

	public static void main(String[] args) {
		Set<Integer> s=new TreeSet<Integer>();
		for(int i=1;i<=9;i++)
		{
			s.add(i);
			
		}
		
		if(s.contains(7))
		{
			List<Integer> li= new ArrayList<Integer>();
			li.add(7);
			System.out.println(li);
		}
		
	}

}
