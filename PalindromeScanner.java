import java.util.Scanner; 
public class PalindromeScanner{
	public static void main(String[] args) {
		System.out.println("enter a word"); 

		Scanner sc = new Scanner(System.in); 
		String word = sc.nextLine(); 

		boolean ans = isPalindrome(word);
		System.out.println(ans); 
	}
	public static boolean isPalindrome(String str){

		String x = str.toLowerCase(); 
		String word = "";
		for (int i  =0 ; i < x.length() ; i++ ) {
			if (x.charAt(i) != ' ') {
				word += x.charAt(i); 
			}
		}
		for (int i = 0; i < word.length()/2 ; i++ ) {
			if (word.charAt(i) != word.charAt(word.length()-1-i)) {
				return false;
			}
		}
		return true; 
	}
}