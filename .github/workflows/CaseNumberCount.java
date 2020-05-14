package ninetydaysworkout;

public class CaseNumberCount {
	public static void main(String[] args) {
		String input="It is Work from Home not Work for Home";
		
		//approach 1
		 int upper = 0, lower = 0, number = 0, special = 0, space=0; 
		  
	        for(int i = 0; i < input.length(); i++) 
	        { 
	            char ch = input.charAt(i); 
	            if (ch >= 'A' && ch <= 'Z') 
	                upper++; 
	            else if (ch >= 'a' && ch <= 'z') 
	                lower++; 
	            else if (ch >= '0' && ch <= '9') 
	                number++; 
	            else if(ch==' ')
	                space++; 
	            else
	            	special++;
	        } 
	  
	        System.out.println("Lower case letters : " + lower); 
	        System.out.println("Upper case letters : " + upper); 
	        System.out.println("Number : " + number); 
	        System.out.println("spaces :" +space);
	        System.out.println("Special characters : " + special);
	        
	        //approach 2 using ascii
	        
	        char ch2;
	        int uppercase=0,lowercase=0;
	        for(int i=0;i<input.length();i++)
	        {
	            ch2 = input.charAt(i);
	            int asciivalue = (int)ch2;
	            if(asciivalue >=65 && asciivalue <=90){
	                uppercase++;
	            }
	            else if(asciivalue >=97 && asciivalue <=122){
	                lowercase++;
	            }
	        }
	        System.out.println("No of lowercase letter : " + lowercase);
	        System.out.println("No of uppercase letter : " + uppercase);
	    } 
	 
	}


