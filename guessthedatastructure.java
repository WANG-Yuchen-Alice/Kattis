package week4;

//Create 4 data structure & check which one gives the input 

import java.io.*;
import java.util.*;

public class guessthedatastructure {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        String str = br.readLine();
        while(str != null) {
        	
        	Queue<Integer> Q = new LinkedList<Integer>();
        	PriorityQueue<Integer> PQ = new PriorityQueue<Integer>(Comparator.reverseOrder());
        	Stack<Integer> S = new Stack<Integer>();
        	
        	boolean q = true;
        	boolean pq = true;
        	boolean s = true;
        	
        	int N = Integer.parseInt(str);
        	for(int i=0; i<N ; i++) {
        		st = new StringTokenizer(br.readLine());
        		int cmd = Integer.parseInt(st.nextToken());
        		int num = Integer.parseInt(st.nextToken());
        		if(cmd == 1) {//insert
        			S.add(num);
        			Q.add(num);
        			PQ.add(num);  		
        		}else {//remove/poll
        			int s_num = 0, q_num = 0, pq_num = 0; 
        			
        			if(!S.empty())
        				s_num = S.pop();
        			if(!Q.isEmpty())
        				q_num = Q.poll();
        			if(!PQ.isEmpty())
        				pq_num = PQ.poll();
        			if(s_num != num)
        				s = false;
        			if(q_num != num)
        				q = false;
        			if(pq_num != num)
        				pq = false;
        		}
        	}
        	
        	if((s && pq && q)||(s && pq)||(s && q)||(q && pq)){ //>1 ds
        		pw.println("not sure");
        	}else if(s){
        		pw.println("stack");
        	}else if(pq) {
        		pw.println("priority queue");
        	}else if(q){
        		pw.println("queue");
        	}else {
        		pw.println("impossible");
        	}	
        	pw.flush();
        	
        	str = br.readLine();
		}
        
	}

}
