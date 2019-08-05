package TakeHome4;

import java.io.*;
import java.util.*;

//Implement basic Graph DS (edge list/adj list) with Minimum Spanning Tree: Kruskal's Algorithm
//Details:
//Only x,y coordinates of vertices are provided. Therefore, form edge list by calculating weight of edges
//Possible permutations of edges = edges in complete graph: E = V*(V-1)/2 or diagonal half of adj matrix
//Sort all edges according to minimum weight & select edges s.t. there will be no cycle between the selected vertices
//Compute & sum weight of all selected edges that has formed the MST

public class islandhopping {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        //Number of cases
        int n = Integer.parseInt(br.readLine());    
        for(int i=0; i<n ; i++) {
            
            //Number of islands
            int m = Integer.parseInt(br.readLine());
            //Initialize all island coordinates in current test case
            double[][] island = new double[m][2];
            for(int j=0; j<m ; j++) {
                st = new StringTokenizer(br.readLine());
                island[j][0] = Double.parseDouble(st.nextToken()); //x-coordinate
                island[j][1] = Double.parseDouble(st.nextToken()); //y-coordinate
            }
            
            //Create edge list
            LinkedList<edge> edgelist = new LinkedList<edge>(); //total number of edges: E = V*(V-1)/2
            for(int j=0; j<m ; j++) {
                //Compute every permutation of edges
                for(int k=j+1; k<m ; k++) {//column only counts until row index (diagonal half of adj matrix)
                    double diff = dist(island[j][0],island[k][0],island[j][1],island[k][1]); //distance between islands
                    edgelist.add(new edge(diff,j,k)); //add edge to edgelist
                }
            }
            
            //Sort edge list by increasing weight
            if (edgelist != null)
            	Collections.sort(edgelist);
            
            //Test for cycles
            UnionFind uf = new UnionFind(m); //all islands are disjoint sets at first
            double mst_cost = 0.0;
            while(uf.numSets > 1) {
                edge curr = edgelist.poll();
                double w = curr.getWeight();
                if (!uf.isSameSet(curr.A(), curr.B())) { // if no cycle
                    mst_cost += w; // add weight w of edge to MST
                    uf.unionSet(curr.A(), curr.B()); // link these two vertices
                }
            }
            
            //Output min. weight of MST
            pw.println(mst_cost);
        }
        
        pw.flush();
        br.close();
    }
    
    public static double dist(double x1, double x2, double y1, double y2) {
        return Math.hypot((y1 - y2),(x1 - x2)); //Pythagoras Theorem
    }
}

class edge implements Comparable<edge>{
    private double weight;
    private int A; //first island
    private int B; //second island
    public edge(double weight, int A, int B) {
        this.weight = weight;
        this.A = A;
        this.B = B;
    }
    public double getWeight() {
        return weight;
    }
    public int A() {
        return A;
    }
    public int B() {
        return B;
    }
	@Override
	public int compareTo(edge o) {
		return Double.compare(this.weight, o.weight);//***NOTE: MUST USE DOUBLE COMPARATOR (otherwise Kattis RTE)//
	}   
}


//Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
class UnionFind {
	public int[] p;
	public int numSets;
	//Constructor//
	public UnionFind(int L) {
		p = new int[L];
		numSets = L;
		//Create nodes
        for (int i = 0; i < L; i++) { 
        	p[i] = i; //Initially, all are in their own set (representative)  
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
		p[aRoot] = bRoot;
		numSets--;				
	}
	//Finds representative of x's set
	public int findSet(int x) {
		if(p[x] != x) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
	//Determine if two belong to same set
	public boolean isSameSet(int x, int y) {
		return(findSet(x) == findSet(y));
	}
}
