package week0;
import java.util.Scanner;
//Revise simple IO & if-else
public class judgingmoose {
	
	public static void main(String[] args){
		Scanner scanned = new Scanner(System.in);
		int x = scanned.nextInt();
		int y = scanned.nextInt();
		
		if (x == 0 && y == 0){
			System.out.println("Not a moose"); // shortcut: ctrl+space after typing sysout
		}else {
			if (x == y) {
				System.out.println("Even " + (x+y));
			}
			if (x > y) {
				System.out.println("Odd " + (2*x));
			}
			if (x < y) {
				System.out.println("Odd " + (2*y));
			}
		}
		scanned.close();
	}
}
