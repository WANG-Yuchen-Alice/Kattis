package Lab8;
import java.util.*;
import java.io.*;

//Implement BFS to explore neighboring cells of a map grid to see if it is land or cloud,
//which they could be all part of same land
//Details: 
//For each cell in map, if it is land & unvisited, perform BFS on neighbors to mark then as visited (as part of same land)
//For every new complete land discovered, the minimum no. of land increases by 1.
//Cloud cells are considered as land if it's neighbor is also land, otherwise it is considered as water

public class island {

    public static LinkedList<int[]> q = new LinkedList<int[]>();
    public static char[][] planet;
    public static int[][] visited;
    public static int r;
    public static int c;
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        planet = new char[r][c];
        visited = new int[r][c];
        
        //Initialize planet map
        for(int i=0; i<r ; i++) {
        	planet[i] = br.readLine().toCharArray(); 
        }
        br.close();
        
        //Search for Land
        int min_land = 0;
        for(int i=0; i<r ; i++) {//For each cell in grid
        	for(int j=0; j<c ; j++) {
        		//Current grid is land & not visited
        		if((planet[i][j] == 'L') && (visited[i][j] == 0)) {
        			visited[i][j] = 1;
        			int[] curr = new int[2];
        			curr[0] = i;
        			curr[1]= j;
        			q.add(curr); //add r,c int[] of location to queue
        			BFS(); //perform BFS
        			min_land++;
        		}	
        	}
        }
       
        pw.println(min_land);
        pw.flush();
	}
	
	//Perform BFS on current cell in grid
	public static void BFS() {
		int[] curr;
		int x;
		int y;
		int[] q_in;
		
		while(!q.isEmpty()) {
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			//Up
			if((x > 0) && (visited[x-1][y] == 0) && (planet[x-1][y] != 'W')) {
				q_in = new int[2];//Note: initialize new pointer every time
				q_in[0] = x-1;//otherwise, when add to the queue, it is considered same array
				q_in[1] = y;//and will not be added to queue (only q_in values will be changed)
				q.add(q_in);
				visited[x-1][y] = 1;//must flag neighbor cell as visited during enqueue,
			}			//instead of dequeue, otherwise BFS will run on every neighboring cell
			//Down
			if((x < r-1) && (visited[x+1][y] == 0) && (planet[x+1][y] != 'W')) {
				q_in = new int[2];
				q_in[0] = x+1;
				q_in[1] = y;
				q.add(q_in);
				visited[x+1][y] = 1;
			}
			//Left
			if((y > 0) && (visited[x][y-1] == 0) && (planet[x][y-1] != 'W')) {
				q_in = new int[2];
				q_in[0] = x;
				q_in[1] = y-1;
				q.add(q_in);
				visited[x][y-1] = 1;
			}
			//Right
			if((y < c-1) && (visited[x][y+1] == 0) && (planet[x][y+1] != 'W')) {
				q_in = new int[2];
				q_in[0] = x;
				q_in[1] = y+1;
				q.add(q_in);
				visited[x][y+1] = 1;
			}
		}
	}
}
