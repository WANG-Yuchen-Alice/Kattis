package Lab4;
import java.util.*;
//Difficult **

//Details: Using Hash Map to map string key(input) to an integer value
//Use value in Hash Map to track frequency of keys(how many of input contains same string)
   
public class conformity {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		//Array of hash key; each array contains key to value(combo of modules)
		ArrayList<Integer> arr;
		
		//Hash Map to map module combo(string) to key (int)
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		
		for (int i=0; i<N; i++) {
			//Temp array to store 5 input numbers
			Integer[] temp = new Integer[5]; 
			//Collect input line by line
			for (int j=0; j<5; j++) {
				temp[j] = sc.nextInt();
			}
			
			Arrays.sort(temp); //Sort current frosh module in ascending number
			
			//Convert to string
			String t = Arrays.toString(temp);
			
			//Check if Hash Map already has this string module combo
			//If don't have:
			if (!hm.containsKey(t)) {
				hm.put(t,1); //Put string(module combo) into Hash Map with frequency 1
			} else {
				hm.put(t,(hm.get(t)+1));//increment frequency by 1
			}
		}
			
		sc.close();
		
		//Copy hash values(frequency of keys= module combo string) to array list
		arr = new ArrayList<Integer>(hm.values()); 
		
		int max = 1; //Record maximum frequency 
		int count = 0; //Count of student most pop
		//Iterate through hash values
		for (Integer i : arr) {
			if (max < i) {//if new max found
				count = i;// update count of new (most popular) number of students
				max = i;//update max tracker
			} else if (max == i) {//if >1 rank one, add their constituent student count
				count = count + i;
			}
		}

		//Output number of (most popular) students
		System.out.println(count);
	}
}
		

