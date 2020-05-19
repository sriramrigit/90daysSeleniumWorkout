package ninetydaysworkout;

import java.util.ArrayList;
import java.util.List;

public class PrintList {

	public static void main(String[] args) {
		//char[] ch= {'B','u','g','a','t','t','i',' ','C','h','i','r','o','n'};
		String ch1="Buggati Chiron";
		List<Character> li=new ArrayList<Character>();
		for(int i=0;i<ch1.length();i++)
		{
			li.add(ch1.charAt(i));
			System.out.println(li.get(i));
		}
		
	}

}
