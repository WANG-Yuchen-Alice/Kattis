package week2;
import java.util.Scanner;
import java.util.Arrays;
//Bubble Sort Simulation
public class mjehuric {

	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		
		int[] array = new int[5]; 
		for (int i=0; i<=4 ; i++) {
			array[i] = scanned.nextInt();
		}
		scanned.close();
		
		int[] correct = new int[] {1,2,3,4,5};
		int temp;
		int[] clone = new int[5];
		while(!(Arrays.equals(array,correct))) {
			
			for (int i=0; i<=3 ; i++) {
				clone = array.clone();
				
				if (array[i] > array[i+1]) {
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
				
				if (!(Arrays.equals(array,clone))) {
					System.out.printf("%d %d %d %d %d\n",array[0],array[1],array[2],array[3],array[4]);
				}
			}
			
		}
	}
}
