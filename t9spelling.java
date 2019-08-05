package Lab1;
import java.util.Scanner;

public class t9spelling {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine()); // cannot use nextInt here then followed by nextLine below
		// because sc.nextInt() reads the next Integer e.g. 1/nhi/n, reads 1
		// while sc.nextLine() reads until the end of line, \n
		char[] alpha = new char[1000]; // initialize character array
		
		
		for (int i = 0; i < n; i++) {
			String str = ""; // initialize output string (number)
			
			alpha = sc.nextLine().toCharArray();
			
			for (int j = 0; j < alpha.length ; j++) {
				 Integer temp = alphaToNum(alpha[j]); //change alphabet to numeric; using INT wrapper class
				 str += temp.toString();
				 
				 if ((j < (alpha.length-1))&&(alphaToNum(alpha[j+1])%10 == alphaToNum(alpha[j])%10)) {
					 str += " ";// if alphabet & its adjacent neighbor belong to the same number button
				 }				// add space
				 
			}
			System.out.println("Case #"+(i+1)+": "+str);
		}
		sc.close();
	}
	
	public static int alphaToNum(char c) {
		int num = 0;

		switch (c) {
			case 'a' : num = 2;
				break;
			case 'b' : num = 22;
				break;
			case 'c' : num = 222;
				break;
			case 'd' : num = 3;
				break;
			case 'e' : num = 33;
				break;
			case 'f' : num = 333;
				break;
			case 'g' : num = 4;
				break;
			case 'h' : num = 44;
				break;	
			case 'i' : num = 444;
				break;	
			case 'j' : num = 5;
				break;	
			case 'k' : num = 55;
				break;	
			case 'l' : num = 555;
				break;	
			case 'm' : num = 6;
				break;	
			case 'n' : num = 66;
				break;	
			case 'o' : num = 666;
				break;	
			case 'p' : num = 7;
				break;	
			case 'q' : num = 77;
				break;	
			case 'r' : num = 777;
				break;
			case 's' : num = 7777;
				break;	
			case 't' : num = 8;
				break;	
			case 'u' : num = 88;
				break;	
			case 'v' : num = 888;
				break;	
			case 'w' : num = 9;
				break;	
			case 'x' : num = 99;
				break;	
			case 'y' : num = 999;
				break;	
			case 'z' : num = 9999;
				break;	
			case ' ' : num = 0;
				break;
			default : break;
		}	
		
		return num; 
	}


}
