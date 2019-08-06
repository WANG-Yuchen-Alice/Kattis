package week5;

import java.util.*;
import java.io.*;

public class tenkindsofpeople {
    public static char[][] map;
    public static boolean[][] visited;
    public static int R, C;
    public static char numsys;
    public static UnionFind ufds;
    

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        //Input map 
        map = new char[R][C];
        for(int i=0; i<R ; i++) {
        	map[i] = br.readLine().toCharArray();
        }
        visited = new boolean[R][C];
        ufds = new UnionFind(R*C); //disjoint sets for each cell  
        
        //Pre-processing//
        //Perform DFS on every cell to union cell pathway connectivity
        for(int i=0; i<R ; i++) {
        	for(int j=0; j<C ; j++){
            	if(map[i][j] == '1') {
            		numsys = '1';
            	}else {
            		numsys = '0';
            	}
        		DFS(i,j);
        	}	
        }
        
        //n queries -> Note: number of queries can be up to 1000, therefore need to pre-process(DFS) once!
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0 ; i<n ; i++) {

        	st = new StringTokenizer(br.readLine());
        	int r1 = Integer.parseInt(st.nextToken()) -1;//1-index to 0-index
        	int c1 = Integer.parseInt(st.nextToken()) -1;		
        	int r2 = Integer.parseInt(st.nextToken()) -1;
        	int c2 = Integer.parseInt(st.nextToken()) -1;

        	if(ufds.isSameSet(r1*C + c1, r2*C + c2)) {//visited = same set
        		if(map[r1][c1] == '0')
        				pw.println("binary");
        		else
        				pw.println("decimal");
        	}else {
        			pw.println("neither");
        		}
        	}
        
        

    	pw.flush();
	}
	
	public static void DFS(int r, int c) {
		if(visited[r][c]){//cell visited
			return;
		}		
		visited[r][c] = true;//Note: matrix index of cell = i x C + j
		
		//visit all neighbors
		//UP
		if((r > 0) && (map[r-1][c] == numsys)) {
			ufds.unionSet(r*C + c, (r-1)*C + c); 
			DFS(r-1,c);
		}
		//LEFT
		if((c > 0) && (map[r][c-1] == numsys)) {
			ufds.unionSet(r*C + c, r*C + (c-1)); 
			DFS(r,c-1);
		}
		//DOWN
		if((r < R-1) && (map[r+1][c] == numsys)) {
			ufds.unionSet(r*C + c, (r+1)*C + c); 
			DFS(r+1,c);
		}
		//RIGHT
		if((c < C-1) && (map[r][c+1] == numsys)) {
			ufds.unionSet(r*C + c, r*C + (c+1)); 
			DFS(r,c+1);
		}
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
