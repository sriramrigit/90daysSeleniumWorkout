package ninetydaysworkout;

public class UserNameValidator {

	public static void main(String[] args) {
		String input = "Testleaf@123";
		String regex = "[a-zA-Z0-9$_.@]{8,}$";
		   if(input.matches(regex))
		   {
			   System.out.println("The string: " + input);
			   System.out.println("Is the above string valid? " + "Yes");
		   }
		   else
		   {
			   System.out.println("Pattern not matched");
		   }
	}

}
