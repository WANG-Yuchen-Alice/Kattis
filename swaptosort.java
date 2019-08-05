package week4;
import java.util.*;

import java.io.*;

//Given array of decreasing numbers N to 1, find whether it is possible to sort array in ascending order
//by swapping between a & b integer, K pair are given (a1,b1), ... , (ak,bk) & can be used repeatedly

public class swaptosort {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//Create Disjoint Sets
		UnionFind ufds = new UnionFind(N+1);
		
		//Swap pairs
		for(int i=0; i<K ; i++) {
	        st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ufds.unionSet(a,b);//union pair, since they can be swap, they can be at each others position
		}
        
		
		//Prove false for each integer
		boolean possible = true;
		for(int i=1; i<=N ; i++) {
			if(!ufds.isSameSet(i,N-i+1))//if not reachable by pair
				possible = false;
			
		}
		
		//Possible to swap until correct ascending order?
		if(possible)
			pw.println("Yes");
		else
			pw.println("No");
		pw.flush();
	}

}

class UnionFind {
	private int[] drawers;//drawer[i] records parent of the drawer i+1; 
	private int[] rank;
	private int[] size;
	//Constructor//
	public UnionFind(int L) {
		drawers = new int[L];
		rank = new int[L];
		size = new int[L];
		//Create nodes
        for (int i = 0; i < L; i++) { 
            //Initially, all drawers are in their own set (representative) 
            drawers[i] = i;//drawer number represented in 0 index
            rank[i] = 0;
            size[i] = 1;
        }
	}
	//Methods//
	//Unite a's & b's set (Union-by-rank)
	public void unionSet(int a, int b) {
		//Get a's set & b's set representative
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) {//Both belong to same set
			return;
		}
		if(rank[aRoot] > rank[bRoot]) {//a's rank more than b's rank
			drawers[bRoot] = aRoot;			
			size[aRoot] += size[bRoot];
		}else if (rank[aRoot] < rank[bRoot]){//a's rank less than b's rank
			drawers[aRoot] = bRoot;
			size[bRoot] += size[aRoot];
		}else {//a's equal rank as b's
			drawers[aRoot] = bRoot;//a's parent is now b
			size[bRoot] += size[aRoot];//add size of a to b
			rank[bRoot]++;//increment result tree rank by 1
		}
				
	}
	//Finds representative of x's set
	public int findSet(int x) {
		//if x's parent is not itself, it is not representative of its set
		if(drawers[x] != x) {
			//recursively call find at x's parent, until representative is found
			drawers[x] = findSet(drawers[x]);
			//if x's parent is not rep., its parent is now set to set rep. (path compression)
		}
		return drawers[x];
	}
	//Check if both element are from the same set
	public boolean isSameSet(int a, int b) {
		return findSet(a) == findSet(b);
	}

}