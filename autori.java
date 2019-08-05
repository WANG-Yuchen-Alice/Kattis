package week1;
import java.util.Scanner;

//NEW: Java StringTokenizer, String split

public class autori {

	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		String input = scanned.next();
		scanned.close();
		String[] words = input.split("-"); //split words by removing -
		
		char[] output = new char[words.length];
		
		for (int i =0; i<words.length ; i++) {
			output[i] = words[i].charAt(0); //character at string position 0
			System.out.printf("%c",output[i]);
		}
		
	}

}
