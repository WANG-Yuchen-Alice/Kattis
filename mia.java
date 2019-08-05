package week0;
import java.util.Scanner;
//Revise simple function & if-else
public class mia {
	
	public static void main(String[] args) { // void means method no return value
		Scanner scanned = new Scanner(System.in);
		
		while(true) { //Instead of endless loop then break, can use while(scanned.hasNextInt())
			int s0 = scanned.nextInt();
			int s1 = scanned.nextInt();
			int r0 = scanned.nextInt();
			int r1 = scanned.nextInt();
			
			if (Integer.sum(s0+s1,r0+r1) == 0) { //Terminate endless loop if 0 0 0 0
				scanned.close();
				break;
			}
			
			int s = twodigit(s0, s1); //Convert two integers into double digits
			int r = twodigit(r0, r1);
			
			int score_s = score(s);
			int score_r = score(r);
			if (score_s == score_r) {
				System.out.println("Tie.");
			}else if(score_s > score_r) {
				System.out.println("Player 1 wins.");
			}else {
				System.out.println("Player 2 wins.");
			}
		}
	}
	
	public static int twodigit(int d1, int d2) {
		int d;
		if (d1 >= d2) {
			d = d1*10 + d2;
		}else {
			d = d2*10 + d1;
		}
		return d;
	}
	
	public static int score(int num) {
		if (num == 21) {
			return 1000;
		}else if (num%10 == num/10){ //two digits are same
			return num*10;
		}else {
			return num;
		}
	}
	//           (mia)
	//high-> low: 21 , 66, 55, 44, 33, 22, 11, 65, 64, 63, 62, 61, 54, 53, 52, 51, 43, 42, 41, 32, 31
	//score:     1000, 660,550,440,330,220,110,65, 64, 63 ...
}
