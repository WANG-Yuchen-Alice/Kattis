package week6;
	
import java.util.*;
import java.io.*;

//MST problem: Form MST to connect all outpost
//dges = transreceiver distance D
//Vertices = outposts
//Satellites S can be given to outposts with farthest distance, 
//thereby reducing edges of MST by S-1 (S vertices)
//Find maximum edge in the reduced MST


public class arcticnetwork {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st;
	        
        //No. of test cases
        int N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	//No. of satellites
        	int S = Integer.parseInt(st.nextToken());
        	//No. of outposts
        	int P = Integer.parseInt(st.nextToken());
        	
        	Coordinates[] outpost = new Coordinates[P];
        	//Coordinates of outposts
        	for(int j=0; j<P; j++) {
        		st = new StringTokenizer(br.readLine());
        		double x = Double.parseDouble(st.nextToken());
        		double y = Double.parseDouble(st.nextToken());
        		outpost[j] = new Coordinates(x,y,j);
        	}
        	
        	//Create Edge List
        	PriorityQueue<Edge> EdgeList = new PriorityQueue<Edge>();//Sort edges by smallest weight
        	for(int j=0; j<P; j++) { //all possible edges, complete graph
        		for(int k=j+1; k<P; k++) {
        			double w = Math.hypot(outpost[j].x - outpost[k].x, outpost[j].y - outpost[k].y);
        			EdgeList.add(new Edge(outpost[j],outpost[k],w));
        			EdgeList.add(new Edge(outpost[k],outpost[j],w));//bi-directional weighted edge
        		}
        	}
        	
        	//Run Kruskal's Algorithm
        	UnionFind ufds = new UnionFind(P);
        	ArrayList<Edge> D = new ArrayList<Edge>();//Store edge weight of MST
        	while(!EdgeList.isEmpty()) {
        		Edge curr = EdgeList.poll();
        		if(!ufds.isSameSet(curr.a.ID, curr.b.ID)) {//prevent cycle
        			D.add(curr);//part of MST
        			ufds.unionSet(curr.a.ID, curr.b.ID);
        		}
        	}
	        	
        	//Has more than 1 edge
        	if(D.size() >0) {
        		Edge output = D.get((D.size()-1)-(S-1));//(S-1)th highest weight edge; 
        		pw.printf("%.2f\n",output.w);
        		//Note: edges are inserted according to ascending edge weight order
        	}else {//No edges, only 1 outpost
        		pw.print("0.00");
        	}
        }//end of current test case
        pw.flush();
	}
	
}
	
class Coordinates {
	public int ID;
	public double x;
	public double y;
	Coordinates(double x, double y, int ID){
		this.x = x;
		this.y = y;
		this.ID = ID;
	}
}

class Edge implements Comparable<Edge>{
	public Coordinates a;
	public Coordinates b;
	public double w;
		Edge(Coordinates a, Coordinates b, double w){
		this.a = a;
		this.b = b;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.w, o.w);
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