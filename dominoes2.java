package week5;

import java.util.*;
import java.io.*;

//3 cases:
//1. normal straight line tile sequence
//2. cyclic tile sequence (do not visit fallen tiles again)
//3. manually fall tile has already fallen (by other sequences)

public class dominoes2 {
	
	public static ArrayList<ArrayList<Integer>> adjList;
	public static Queue<Integer> q;
	public static long sum;
	public static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st;
        
        int total = Integer.parseInt(br.readLine());
        
        for(int i=0; i<total; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken()); //no. of domino tiles (numbered: 1 to n)
        	int m = Integer.parseInt(st.nextToken()); //m lines: tile x cause tile y to fall
        	int l = Integer.parseInt(st.nextToken()); //n lines: tiles knock by hand
        	
        	adjList = new ArrayList<ArrayList<Integer>>();
        	for(int j=0; j<n+1; j++) {
        		adjList.add(new ArrayList<Integer>());//create blanks for adjacency list
        	}
        	
        	for(int j=0; j<m; j++) {
        		st = new StringTokenizer(br.readLine());
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
        		adjList.get(a).add(b); //unidirectional edge
        	}
        	
        	sum = 0;
        	q = new LinkedList<Integer>();
        	visited = new boolean[n+1];
        	for(int j=0; j<l; j++) {
        		int x = Integer.parseInt(br.readLine());
        		if(!visited[x]) {//must check that even though we manually fall tile, has it already fallen?
        			q.add(x);
        			visited[x] = true;
        		}
        		traverse();
        	}
        	pw.println(sum);
        }
        pw.flush();
	}

	public static void traverse() {//check for node's neighbors in adjacency list
		while(!q.isEmpty()) {
			int x = q.poll();
			sum++;
			if(!adjList.get(x).isEmpty()) {
				for(int i : adjList.get(x)) {
					if(!visited[i]) {
						q.add(i);
						visited[i] = true;
					}
				}
			}
		}
	}
}
