package week6;

import java.util.*;
import java.io.*;

//Floyd Warshall's APSP problem + find arbitrarily short path (path that contains negative cycle)
//https://cp-algorithms.com/graph/finding-negative-cycle-in-graph.html

public class allpairsshortestpath {
	
	public static long[][] D; //Shortest Dist
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st;
        
        while(true) {
        
        	st = new StringTokenizer(br.readLine());
        	//Number of nodes
        	int n = Integer.parseInt(st.nextToken());
        	//Number of edges
        	int m = Integer.parseInt(st.nextToken());
        	//Number of queries
        	int q = Integer.parseInt(st.nextToken());
        	
        	if((n==0)&&(m==0)&&(q==0)) {//Terminate Loop
            	pw.flush();
        		return;
        	}
        	
        	//Initialize Distance matrix
        	D = new long [n][n];
        	for(int i=0; i<n; i++) {
        		Arrays.fill(D[i], Integer.MAX_VALUE);
        		D[i][i] = 0;//diagonal is 0
        	}        	
        	
        	//All edges
        	for(int i=0; i<m; i++) {
        		st = new StringTokenizer(br.readLine());
        		int u = Integer.parseInt(st.nextToken());//edge from u to v
        		int v = Integer.parseInt(st.nextToken());
        		int w = Integer.parseInt(st.nextToken());
        		if((u != v) && (D[u][v] > w))
        			D[u][v] = w;
        	}
              	
        	//Floyd Warshall's - O(V^3)
			for(int k=0; k<n; k++)
				for(int i=0; i<n; i++)
					for(int j=0; j<n; j++) 
						if(D[i][k] < Integer.MAX_VALUE && D[k][j] < Integer.MAX_VALUE)
							D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
        				
        	for(int i=0; i<n; i++) 
        		for(int j=0; j<n; j++)
        			for(int k=0; k<n; k++)
        		          if(D[i][k] < Integer.MAX_VALUE && D[k][j] < Integer.MAX_VALUE &&  D[k][k] < 0) 
        		        	  D[i][j] = Integer.MIN_VALUE;      
        	//negative cycle exist & vertex k is part of it, when reaches itself with d[k][k]<0
        	//arbitrarily short path between vertices i and j exist
        	//if and only if, vertex k is reachable from i and also from j
        	
        	//Incoming queries
        	for(int i=0; i<q; i++) {
        		st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		
        		if(D[a][b] == Integer.MAX_VALUE)
        			pw.println("Impossible");
        		else if(D[a][b] == Integer.MIN_VALUE) 
        			pw.println("-Infinity");
        		else 
        			pw.println(D[a][b]);
        	}
        	pw.println(" ");
        }
	}
}

