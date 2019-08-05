package TakeHome3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

//Use Union Find DS to connect drawers(if item can be place in drawer A or B, connect A-to-B)

//Details:
//All drawers starts off as individual sets.
//At each iteration, sets are connected if there is affiliation between drawers.
//Since we are not interested in positions of items in which drawer, no need to track movements.
//Instead, we keep track of size of set to see if there is sufficient space to insert new item.
//Capacity of empty drawers are tracked in via size of set.
//If capacity is full, impossible to shift to get empty slots, therefore fail.
//Otherwise, items can be shifted (not interested in combination) to make space for new input.

//Since at each iteration, the set in which drawer A & B are in, are connected. 
//So there is no need to differentiate between A's set & B's set.
//In A & B's set, we also do not care if you insert in A or B or their set members
//Therefore, first 4 rules are considered to be 1 case. 

//Case 1: set got space, Case 2: set no space

public class ladice {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		//Create Disjoint Sets
		UnionFind ufds = new UnionFind(L);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			a--;//convert drawer no. to 0 index
			int b = Integer.parseInt(st.nextToken());
			b--;
			
			//Unify set with common item affiliation
			ufds.unionSet(a, b);
			
			//Empty drawer in a or b's set (including a & b, since they are in same set)
			if (ufds.gotSpace(a)) {
				ufds.occupy(a);
				pw.println("LADICA");
			} else {//Trash
				pw.println("SMECE");	
			}
		}
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
	//Checks if set has empty drawers|| Note: size[root] store size of set
	public boolean gotSpace(int x){
		if(size[findSet(x)] > 0) //< root size occupied
			return true;
		else
			return false;
	}
	//One drawer in set is occupied
	public void occupy(int x) {
		size[findSet(x)]--;//size of set reduces by one
	}
}


