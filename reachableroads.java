package week5;

import java.io.*;
import java.util.*;
import week5.tenkindsofpeople;

public class reachableroads {

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st;
        
        //Number of cities
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++) {
        	
        	//Number of vertices(end point)
        	int m = Integer.parseInt(br.readLine());
        	UnionFind ufds = new UnionFind(m);
        	
        	//Number of edges(road)
        	int r = Integer.parseInt(br.readLine());
        	for(int j=0; j<r; j++) {
        		st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());		
        		ufds.unionSet(a, b);//if end points are connected by road, union them together since they are reachable
        	}
        	
        	//Check how many disjoint sets
        	HashSet<Integer> hs = new HashSet<Integer>();
        	int ds_num = 0;//no. of disjoint sets
        	for(int j=0; j<m; j++) {
        		int curr = ufds.findSet(j);//find number of components = number of disjoint sets
        		if(!hs.contains(curr)) {
        			hs.add(curr);
        			ds_num++;
        		}
        	}
        	
        	//Print number of edges to join disjoint sets
        	pw.println(ds_num - 1);
        	pw.flush();
        }
	}

}
