package week6;

import java.io.*;
import java.util.*;

//Unweighted SP graph problem; but fastest solution doesn't use BFS

public class emergencycontestrunning {
	
	public static long n;
	public static long k;

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //Last node index
        n = Long.parseLong(st.nextToken()) - 1;
        //Multiple
        k = Long.parseLong(st.nextToken());
        
        long curr = 0;
        long steps = 0;
        
        if(higherMultiple(curr) != -1){ //Note: cannot traverse linearly, since n & k <= 10^18
        	curr = higherMultiple(curr); //jump to highest multiple of k, if exist & != k
        	steps = k + 1 + (n-curr);
        }else {
        	steps = n; //no jumps, linearly move
        }
        pw.println(steps);
        pw.flush();
	}
	public static long higherMultiple(long num) {
		if(n-(n%k) > k && num < n-(n%k))//has higher multiple of k, other than k & highest multiple
			return n-(n%k);
		else
			return -1;
	}

}
