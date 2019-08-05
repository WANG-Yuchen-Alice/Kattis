package Lab3;
import java.util.*;

//Details: Using 2 Stacks to find matching numbers in a number sequence
//Pop top of stack 1 to 2 & find matching pair at top of each pile
//There must be at least one pair of number (side-by-side) in the remaining number sequence,
//else impossible to find match 
//(stack will be empty after popping all to stack 2; w/o finding a consecutive match))

public class pairingsocks {
	
	public static void main(String[] args) {
		Stack<Integer> org = new Stack<Integer>(); //original stack
		Stack<Integer> aux = new Stack<Integer>(); //auxillary stack
		
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		N = 2*N;
		
		//Input line of numbers
		String numbers = sc.nextLine();
		sc.close();
		//Split each spaced integer into string array
		String[] nums = numbers.split(" "); 
		
	    //Input numbers into original stack, sock 1 is at top of pile
		boolean impossible = true;
		for (int i = nums.length - 1; i >= 0; i--){
		    org.push(Integer.parseInt(nums[i])); 
		    
		    if (i >= 1) { //Compare if any consecutive repeated number
		    	if (Integer.parseInt(nums[i]) == Integer.parseInt(nums[i-1])) 
		    			impossible = false;
		    }
		}
		
		//Impossible, because no consecutive repeated numbers -> exit program
		if(impossible) {
			System.out.println("impossible");
			return;
		}
		
		//Put first sock from original pile(stack) to aux pile
		aux.push(org.pop());
		int moves = 1;
		
		//Repeat until all socks paired
		while(N > 0) {	
			//Impossible; no more remaining matching consecutive numbers in stack
			if(org.isEmpty()) {
				System.out.println("impossible");
				return;
			}
			
			//Aux pile should always have at least 1 sock
			if(aux.isEmpty()) {
				aux.push(org.pop());
				moves++;
			}
			
			//Matches pair at top of pile
			if (aux.peek().intValue() == org.peek().intValue()) {
				aux.pop();
				org.pop();
				moves++; //Increment move count
				N -= 2; //Decrement no. of socks by 2
			} else {
				aux.push(org.pop()); //Move top sock to aux pile
				moves++;
			}
			
		}
		
		System.out.println(moves);
		
	}

}
