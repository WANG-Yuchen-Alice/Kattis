package Lab10;
import java.util.*;
import java.io.*;

//Use SSSP Dijkstra (original) algorithm to find shortest path to destination

//Details: You run at 5m/s, Cannon launch you at distance of 50m (process take 2 seconds)
//Vertices: Start, End & Cannons 
//Edges: time taken to travel path

//Case 1: Cannons too far away, path from Source to Sink is shortest, ignore cannons
//Case 2: Use Cannon to travel faster
// 	   a: From Source, need to run to nearest Cannon (by foot)
//	   b: From Cannon to anywhere else, including Sink (by launch)

public class humancannonball {
	public static final double INF = 1000000000;
	public static ArrayList< Double > D = new ArrayList< Double >();
	public static ArrayList< Integer > p = new ArrayList< Integer >();
	public static int V, E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        // Get start & end coordinates
        st = new StringTokenizer(br.readLine());
        double Start_x = Double.parseDouble(st.nextToken());
        double Start_y = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double End_x = Double.parseDouble(st.nextToken());
        double End_y = Double.parseDouble(st.nextToken());
        
        // Get cannon coordinates
        int n = Integer.parseInt(br.readLine());
        V = n+2; //no. of vertices
        E = V*(V-1)/2; //no. of edges in complete graph
        double[][] vertices = new double[V][2];
        vertices[0][0] = Start_x;
        vertices[0][1] = Start_y;
        vertices[V-1][0] = End_x;
        vertices[V-1][1] = End_y;
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            vertices[i][0]  = Double.parseDouble(st.nextToken()); //x coordinate
            vertices[i][1] = Double.parseDouble(st.nextToken()); // y coordinate
        }
        
        //Create Edge Weight Matrix (Edge List)
        double[][] w = new double[V][V];
        for (int i=0; i<V ; i++) {
        	for (int j=i+1; j<V ;j++) {
        		double dist = Math.hypot(vertices[i][0]-vertices[j][0],vertices[i][1]-vertices[j][1]);
        		double t;
        		if(i==0) {//edge between source to sink (ignoring cannon) and source to cannon
        			t = dist/5;
        		}
        		else {//edge between source & cannon, cannon & sink or cannon & cannon
        			t = Math.abs(dist-50)/5 + 2;
        		}
        		w[i][j] = t;//bidirectional
        		w[j][i] = t;
        	}
        }

        int source = 0;
        init_SSSP(source); //source vertex ID is 0
        
        PriorityQueue < NumPair > pq = new PriorityQueue < NumPair > (); 
        pq.offer(new NumPair(source, 0.0));
        
        HashSet<Integer> Solved = new HashSet<Integer>();
        Solved.add(0);
        
        while (!pq.isEmpty()) { // main loop
          NumPair top = pq.poll(); // greedy: pick shortest unvisited vertex
          int u = top.first();
          Solved.add(u);//SP found
          for(int v=0; v<V ; v++) {//for neighbors of vertex u (all other vertices, since complete graph)
        	  if(!Solved.contains(v)) {//also not itself
        		  if(D.get(v) > D.get(u) + w[u][v]) { //can relax?
        			  D.set(v, D.get(u) + w[u][v]); //relax
        			  pq.offer(new NumPair(v, D.get(v))); ///re-enqueue this
        		  }
        	  }
          }
        }
        //Output Shortest Path Estimate to Sink, last vertex: index V-1
        pw.println(D.get(V-1));
        pw.flush();
	}
	
	public static void init_SSSP(int s) { // initialization phase
		D.clear();
		D.addAll(Collections.nCopies(V, INF)); // use 1B to represent INF
		p.clear();
		p.addAll(Collections.nCopies(V, -1)); // use -1 to represent NULL
		D.set(s, 0.0); // this is what we know so far
	}

}

class NumPair implements Comparable<NumPair> {
	  int _first; //vertex
	  double _second; //weight

	  public NumPair(Integer f, Double s) {
	    _first = f;
	    _second = s;
	  }

	  public int compareTo(NumPair o) {
	      return Double.compare(this.second(), o.second());
	  }

	  int first() { return _first; }
	  double second() { return _second; }
}
