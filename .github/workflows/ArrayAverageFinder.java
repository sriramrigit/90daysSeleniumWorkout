package ninetydaysworkout;

public class ArrayAverageFinder {

	public static void main(String[] args) {
		int[] array= {20, 30, 25, 35, -16, 60, -100};
		int size=array.length;
		int sum=0;
		double avg=0;
		for(int i=0;i<size;i++)
		{
			sum=sum+array[i];
		}
		avg=sum/size;
		System.out.println(avg);
	}

}
