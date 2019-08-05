package week4;

import java.util.*;
import java.io.*;

//Sum of numbers in perfect binary tree = 2^(h+1) - 1

public class numbersonatree {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        st = new StringTokenizer(br.readLine());
        int height = Integer.parseInt(st.nextToken());
        
        //Root node is last node of b-bst
        double root = Math.pow(2, height+1) - 1;
        double N = root;
        if(st.hasMoreTokens()) {
        	String traverse = st.nextToken();
        	for(Character c : traverse.toCharArray()) {
        		double move;
        		if(c == 'L') {//left
        			move = root - N + 1;
        		}else {//right
        			move = root - N + 2;
        		}
        		N = N - move;//traverse down path left/right from root
        	}
        }
        
        pw.println((long)N);
        pw.flush();
	}

}
