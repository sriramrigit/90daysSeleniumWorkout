package ninetydaysworkout;

public class ArraySortAndReverse {

	public static void main(String[] args) {
		int [] array = {5,1,33,79,22,11,45};
		int n=array.length;
		for (int i = 0; i < n-1; i++) 
		{
            for (int j = 0; j < n-i-1; j++) 
            {
                if (array[j] > array[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = array[j]; 
                    array[j] = array[j+1]; 
                    array[j+1] = temp; 
                }
            }
		}
		for (int i=0; i<n; ++i)
		{
			System.out.print(array[i] + " "); 
			System.out.println(); 
		}
		//reverse
		for(int i=n-1;i>=0;i--)
		{
			System.out.print(array[i] + " "); 
			System.out.println();
		}
}
	
}