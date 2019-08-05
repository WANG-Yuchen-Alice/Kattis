package week1;
import java.util.Scanner;
// Review: Function
public class zamka {
	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		
		// L <= N <= D, L <= M <= D
		int L = scanned.nextInt();// 1 <= L <= 10,000
		int D = scanned.nextInt();// 1 <= D <= 10,000, L <= D
		int X = scanned.nextInt();// X = sum of digits of N & M; 1 <= X <= 36
		scanned.close();
		
		int min = 10000;
		int max = 1;
		
		for (int i=L; i<= D; i++) {
			 if (intFinder(i,X) == 1) {
				 if (i > max ) {
					 max = i;
				 }
				 if (i < min) {
					 min = i;
				 }
			 }
		}
		
		System.out.println(min);
		System.out.println(max);
	}
	
	public static int intFinder(int num, int X) {
		int sum = 0;
		while(num>0) {
			sum = num % 10 + sum;
			num = num/10;
		}

		if (sum == X) {
			return 1;
		}
		else {
			return 0;
		}

	}
}
