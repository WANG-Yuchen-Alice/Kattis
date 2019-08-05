package Lab1;
import java.util.Scanner;

public class t9spellingV2 {
	private static String[] arr = new String[256];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		//every character is actually represented as an integer from 0 to 255.

		arr['a'] = "2";
		arr['b'] = "22";
		arr['c'] = "222";
		arr['d'] = "3";
		arr['e'] = "33";
		arr['f'] = "333";
		arr['g'] = "4";
		arr['h'] = "44";
		arr['i'] = "444";
		arr['j'] = "5";
		arr['k'] = "55";
		arr['l'] = "555";
		arr['m'] = "6";
		arr['n'] = "66";
		arr['o'] = "666";
		arr['p'] = "7";
		arr['q'] = "77";
		arr['r'] = "777";
		arr['s'] = "7777";
		arr['t'] = "8";
		arr['u'] = "88";
		arr['v'] = "888";
		arr['w'] = "9";
		arr['x'] = "99";
		arr['y'] = "999";
		arr['z'] = "9999";
		arr[' '] = "0";
		
		for (int i=1; i<=n; i++) {
			char[] input = sc.nextLine().toCharArray();
			String output = "";
			
			output = alphaToNum(input[0]);
			for (int j=1; j<input.length; j++) {
				if (alphaToNum(input[j-1]).contains(alphaToNum(input[j]))) {
					output += " ";
				}else if (alphaToNum(input[j]).contains(alphaToNum(input[j-1]))) {
					output += " ";
				}
				output += alphaToNum(input[j]);
				
			}
			
			System.out.println("Case #"+i+": "+output);
		}	
		sc.close();
	}
	
	public static String alphaToNum(char c) {
		return arr[c];
	}

}
