package week1;
import java.util.Scanner;
import java.util.Vector; // or can use import java.util.*;
//NEW: Java class (overkill actually), Java Vector
public class bookingaroom {

	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		int r = scanned.nextInt();
		int n = scanned.nextInt();
		
		Vector v = new Vector(); 
		
		for (int i=1; i<=n ; i++) {
			v.add(scanned.nextInt());
		}
		
		scanned.close();
		
		for (int i=1; i<= r ; i++) {
			if (v.contains(i)) {
				
			}else {
				System.out.println(i);
				return;
			}	
			
		}
		System.out.println("too late");
	}

}
