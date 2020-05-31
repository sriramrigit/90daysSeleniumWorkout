package ninetydaysworkout;

public class MatrixAddition {

	public static void main(String[] args) {
		int[][] array1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] array2 = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
		System.out.println("Additions of two matrices");
		for(int i=0;i<array1.length;i++)
		{
			for(int j=0;j<array2.length;j++)
			{
				System.out.print(array1[i][j]+array2[i][j]+" ");
			}
			System.out.println();
		}
	}

}
