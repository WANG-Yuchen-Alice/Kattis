package misc;
//import java.util.*;

public class solution {
	public static void main(String[] args) {

		 int[] A = {1,1,1,0,1,1};
		 int length = A.length;
	        
		 int[] startwone = new int[length];
		 int[] startwzero = new int[length];
		        
		 //Create compare array
		 //0101 Even
		 //1010
		 //10101 Odd
		 //01010
		 startwone[0] = 1;
		 startwzero[0] = 0;
		 for (int i=1; i<length; i++){
		 	startwone[i] = startwzero[i-1];
		 	startwzero[i] = startwone[i-1];
		 }
		       
		 int count_one = 0;
		 int count_zero = 0;
		        
		 //Determine which has permutation of 1 & 0 yield least differences
		 for (int i=0; i<length; i++){
			 if (A[i] != startwone[i]){
				 count_one++;
			 }   
			 if (A[i] != startwzero[i]){
		         count_zero++;
		     }
		 } 
		        
		 //Output differences
		 if (count_one > count_zero){
			 System.out.println(count_zero);
		 } else { 
		     System.out.println(count_one);
		 }
		
	}
}
