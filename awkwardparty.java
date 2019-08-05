package week3;
import java.util.*;

public class awkwardparty {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		//Records previous person speaking same language 
		HashMap<Integer,Integer> prev = new HashMap<>();
		//Stores minimum count
		HashMap<Integer,Integer> min = new HashMap<>();
		
		boolean perfectAwkward = true;
		
		for(int i=0; i<N; i++) {
			int curr = sc.nextInt(); //current input int
			if(min.containsKey(curr)) { //have received int before
				perfectAwkward = false;
				int space = i - prev.get(curr);//no. of space between prev person of same lang
				if(min.get(curr)>space) {
					min.put(curr,space);//update minimum
				}
			}else { //first time
				min.put(curr, 100000);
				prev.put(curr, i);
			}
			
		}
		sc.close();
		
		if(perfectAwkward) {
			System.out.println(N);
			return;
		} 
		
		int awk = N;
		for(Integer i : min.values()) {
			if(awk>i)
				awk = i;	
		}
		System.out.println(awk);
	}
}
