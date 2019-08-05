package week1;
import java.util.Scanner;
//Revise simple IO, control flow: repetition, selection
public class statistics {
	
	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		
		int casecount = 0;
		
		while (scanned.hasNextInt()) {
			int N = scanned.nextInt();
			int[] array = new int[N]; //Declaring & allocating memory to array
		
			int max = -1000000; //Initialize boundaries for max, min
			int min = 1000000;
			
			for (int i=0; i<N; i++) {
				array[i] = scanned.nextInt();
				if (max < array[i]) {
					max = array[i];
				}
				if (min > array[i]) {
					min = array[i];
				}
			}
			casecount++;
			System.out.println("Case "+casecount+": "+min+" "+max+ " "+(max-min));
		}
		scanned.close();
	}
	
}
