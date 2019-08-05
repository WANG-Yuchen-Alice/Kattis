package week0;
import java.util.Scanner;
//Revise simple IO & for loop
public class timeloop {
	
	public static void main(String[] args) {
		String output = "Abracadabra";
		Scanner scanned = new Scanner(System.in);
		int N = scanned.nextInt();
		scanned.close();
		
		for (int i = 1; i <= N; i++) {
			System.out.println(i+ " " +output);
		}

	}

}
