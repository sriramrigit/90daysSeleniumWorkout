package ninetydaysworkout;

public class EvenStringReverse {

	public static void main(String[] args) {
		String input[]="When the world realise its own mistakes, corona will dissolve automatically".split("[\\s@&.?$+-]+");
		int len = input.length;
		// Final answer to be printed
		String ans = "";
		// Alternate words that get reversed
		String revWords = "";
		// Traverse the entire sentence
		for (int i = 0; i < len; i++) {
			// Take each word
			String words = input[i];
			// Identify the alternative word to reverse
			if (i % 2 != 0) {
				// Traverse from right to left and accumulate the same in 'revWords'
				for (int j = words.length() - 1; j >= 0; j--) {
					revWords = revWords + words.charAt(j);
				}
				// Alternatively accumulate the same in the answer variable to preserve the order.
				ans = ans + revWords + " ";
				// Reset the revWords variable to reverse the next alternate word.
				revWords = "";
			}
			// Accumulate the words to answer variable which are next to the reversed Words.
			else
				ans = ans + input[i] + " ";
		}
		System.out.println(ans);
	}

}
