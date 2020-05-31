package ninetydaysworkout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayCommon {

	public static void main(String[] args) {

		Integer [] array1 = { 1, 2, 5, 5, 8, 9, 7, 10 };
		Integer [] array2 = { 1, 0, 6, 15, 6, 4, 7, 0 };
		
		 Set hashSet = new HashSet(Arrays.asList(array1)); 
		 Set commonElements = new HashSet();        
	        for (int i = 0; i < array2.length; i++) {
	            if (hashSet.contains(array2[i])) {
	                commonElements.add(array2[i]);
	            }
	        }
	        System.out.println("Common elements " + commonElements);
	       
	}

}
