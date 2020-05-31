package ninetydaysworkout;

public class FibonacciReverse {

	public static void main(String[] args) {
		int  sum[]= new int[10];
		sum[0]=0;
		sum[1]=1;
		for(int i=2;i<10;i++)
		{
			sum[i] = sum[i - 2] + sum[i - 1];
		}
		for (int i = 9; i >= 0; i--)  
        { 
      
            // printing array in 
            // reverse order 
            System.out.print(sum[i] +" "); 
        } 
	}

}
