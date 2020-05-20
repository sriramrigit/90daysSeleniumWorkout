package ninetydaysworkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DuplicateFinderList {

	public static void main(String[] args) {
		List<String> li = new ArrayList<String>();
		li.add("A");
		li.add("B");
		li.add("C");
		li.add("D");
		li.add("A");
		li.add("D");
		li.add("E");
		li.add("F");
		Set<String> set=new LinkedHashSet<String>(li);
		//System.out.println(set);
		li.clear();
		li.addAll(set);
		System.out.println(li);
	}

}
