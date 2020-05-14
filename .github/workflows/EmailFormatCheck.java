package ninetydaysworkout;

public class EmailFormatCheck {

	public static void main(String[] args) {
		String email = "bal_aji.c@tunatap.co.uk";
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		   if(email.matches(regex))
		   {
			   System.out.println("The E-mail ID is: " + email);
			   System.out.println("Is the above E-mail ID valid? " + "Yes");
		   }
		   else
		   {
			   System.out.println("Pattern not matched");
		   }
	}

}
