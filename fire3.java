package week6;

import java.io.*;
import java.util.*;

//Use Multi-Source BFS to traverse movement of Joe & each Fire source
//Track their visitations on separate maps (since fire will eventually visit all cells that are not walls)
//Need to differentiate if fire or Joe arrive to cell first

//Note Special case: no fire at all

public class fire3 {
	
	public static Queue<IntegerPair> Q = new LinkedList<IntegerPair>();
	public static Queue<IntegerPair> Fire = new LinkedList<IntegerPair>();
	public static IntegerPair Joe;
	
	public static char[][] map;
	public static int[][] J;
	public static int[][] F;
	
	public static boolean[][] visited;
	public static boolean[][] J_visited;
	
	public static void main(String[] args) throws IOException {
		 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	        StringTokenizer st;
	        
	        st = new StringTokenizer(br.readLine());
	        int r = Integer.parseInt(st.nextToken());
	        int c = Integer.parseInt(st.nextToken());
	        
	        map = new char[r][c];
	        J = new int[r][c];
	        F = new int[r][c];
	        visited = new boolean[r][c];
	        J_visited = new boolean[r][c];
	        
	        for(int i=0; i<r; i++) {
	        	String str = br.readLine();
	        	for(int j=0; j<c; j++) {
	        		map[i][j] = str.charAt(j);
	        		if(map[i][j] == 'J') {//Store starting location of Joe
	        			Joe = new IntegerPair(i,j,false);
	                	J_visited[Joe.x][Joe.y] = true;
	                	J[Joe.x][Joe.y] = 1;
	                	F[Joe.x][Joe.y] = Integer.MAX_VALUE;
	        		}
	        		else if(map[i][j] == 'F') {//Store Starting location of all Fires
	        			IntegerPair f = new IntegerPair(i,j,true);
	        			visited[f.x][f.y] = true;
	        			F[f.x][f.y] = 1;//other than fire source, all cells = inf
	        			Fire.add(f);
	        		}
	        		else if(map[i][j] == '#') {
	        			visited[i][j] = true;
	        			F[i][j] = Integer.MAX_VALUE;
	        		}
	        		else {//map[i][j] == '.'
	        			F[i][j] = Integer.MAX_VALUE;
	        		}
	        	}
	        }
	        
	        //Multi-Source BFS
	        Q.add(Joe);
	        while(!Fire.isEmpty()) {
	        	Q.add(Fire.poll());
	        }
	        
	        while(!Q.isEmpty()) {
	        	IntegerPair curr = Q.poll();
	        	if(!curr.fire) {//JOE
	        		//UP
	        		if(curr.x-1 >=0 && visited[curr.x-1][curr.y] == false && J_visited[curr.x-1][curr.y] == false) {
	        			J[curr.x - 1][curr.y] = J[curr.x][curr.y] + 1;
	        			J_visited[curr.x - 1][curr.y] = true;
	        			Q.add(new IntegerPair(curr.x - 1, curr.y, false));
	        		}
	        		//DOWN
	        		if(curr.x+1 < r && visited[curr.x+1][curr.y] == false && J_visited[curr.x+1][curr.y] == false) {
	        			J[curr.x + 1][curr.y] = J[curr.x][curr.y] + 1;;
	        			J_visited[curr.x + 1][curr.y] = true;
	        			Q.add(new IntegerPair(curr.x + 1, curr.y, false));
	        		}	        			
	        		//LEFT
	        		if(curr.y-1 >=0 && visited[curr.x][curr.y-1] == false && J_visited[curr.x][curr.y-1] == false) {
	        			J[curr.x][curr.y - 1] = J[curr.x][curr.y] + 1;;
	        			J_visited[curr.x][curr.y - 1] = true;
	        			Q.add(new IntegerPair(curr.x, curr.y - 1, false));
	        		}	        			
	        		//RIGHT
	        		if(curr.y+1 < c && visited[curr.x][curr.y+1] == false && J_visited[curr.x][curr.y+1] == false) {
	        			J[curr.x][curr.y + 1] = J[curr.x][curr.y] + 1;;
	        			J_visited[curr.x][curr.y + 1] = true;
	        			Q.add(new IntegerPair(curr.x, curr.y + 1, false));	
	        		}	
	        	} else {//FIRE
	        		//UP
	        		if(curr.x - 1 >=0 && visited[curr.x - 1][curr.y] == false) {
	        			F[curr.x - 1][curr.y] = F[curr.x][curr.y] + 1;
	        			visited[curr.x - 1][curr.y] = true;
	        			Q.add(new IntegerPair(curr.x - 1, curr.y, true));
	        		}
	        		//DOWN
	        		if(curr.x + 1 < r && visited[curr.x + 1][curr.y] == false) {
	        			F[curr.x + 1][curr.y] = F[curr.x][curr.y] + 1;
	        			visited[curr.x + 1][curr.y] = true;
	        			Q.add(new IntegerPair(curr.x + 1, curr.y, true));
	        		}	        			
	        		//LEFT
	        		if(curr.y - 1 >=0 && visited[curr.x][curr.y - 1] == false) {
	        			F[curr.x][curr.y - 1] = F[curr.x][curr.y] + 1;
	        			visited[curr.x][curr.y - 1] = true;
	        			Q.add(new IntegerPair(curr.x, curr.y - 1, true));
	        		}	        			
	        		//RIGHT
	        		if(curr.y + 1 < c && visited[curr.x][curr.y + 1] == false) {
	        			F[curr.x][curr.y + 1] = F[curr.x][curr.y] + 1;
	        			visited[curr.x][curr.y + 1] = true;
	        			Q.add(new IntegerPair(curr.x, curr.y + 1, true));	
	        		}
	        	}	
	        }
	        
	        //Search border of map for traces of Joe's visit (&whether it is earlier than fire)
	        int min = Integer.MAX_VALUE;	        
	        for(int i=0; i<r; i++) {//check first & last column of map
	        	if(J[i][0]!=0 && J[i][0]<F[i][0] && min>J[i][0])
	        		min = J[i][0];
				if(J[i][c-1]!=0 && J[i][c-1]<F[i][c-1] && min>J[i][c-1])
					min = J[i][c-1];
	        }
	        
	        for(int j=0; j<c; j++) {//check first & last row of map
	        	if(J[0][j]!=0 && J[0][j]<F[0][j] && min>J[0][j])
	        		min = J[0][j];
				if(J[r-1][j]!=0 && J[r-1][j]<F[r-1][j] && min>J[r-1][j])
					min = J[r-1][j];
	        }
	        
	        if(min != Integer.MAX_VALUE)
	        	pw.println(min);
	        else
        		pw.println("IMPOSSIBLE");
	        pw.flush();
	}

}

class IntegerPair{
	public int x;
	public int y;
	public boolean fire;
	IntegerPair(int x, int y, boolean fire){
		this.x = x;
		this.y = y;
		this.fire = fire;
	}
}
