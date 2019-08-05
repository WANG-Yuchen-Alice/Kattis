package Lab9;
import java.util.*;
import java.io.*;

//Use Prim's Algorithm to find edges of MST
//Only output V-1 edges with minimal weight

public class lostmap {
	
	public static ArrayList < ArrayList < IntegerTriple > > AdjList;
	public static ArrayList < Boolean > taken;
	public static PriorityQueue < IntegerTriple > pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        //No. of villagers in the region (vertices)
        int n = Integer.parseInt(br.readLine());
        
        //Create Adjacency List
        AdjList = new ArrayList < ArrayList < IntegerTriple > >();
        for (int i = 0; i < n; i++) {
          ArrayList < IntegerTriple > Neighbor = new ArrayList < IntegerTriple >(); // create vector of Integer pair 
          AdjList.add(Neighbor); // store blank vector first
        }
        for (int i = 0; i < n; i++) { // store graph information in Adjacency List
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
        		int w = Integer.parseInt(st.nextToken());
        		if (w != 0) {//store edge that exist only
        			AdjList.get(i).add(new IntegerTriple(w,i,j)); // 0-indexing for vertices
        			AdjList.get(j).add(new IntegerTriple(w,j,i));
        		}
        	}
        }
        br.close();
        
        //Create taken tracker
        taken = new ArrayList < Boolean >(); 
        taken.addAll(Collections.nCopies(n, false));
        //Create Priority Queue
        pq = new PriorityQueue < IntegerTriple > ();
        // Take any vertex of the graph, for simplicity, vertex 0, to be included in the MST
        process(0);

        while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
        	IntegerTriple front = pq.poll();

          if (!taken.get(front.second())) { // we have not connected this vertex yet
            pw.print((front.first()+1)); //this edge added to MST; change to 1-index
            pw.print(" ");
            pw.println((front.second()+1));
            process(front.second()); // enqueue each edge adjacent to this vertex into PQ if not already taken by Tree
          }
        }
        pw.flush();
	}
	
	//Insert neighbors of MST neighbors into PQ
	public static void process(int vtx) {
		taken.set(vtx, true);
		for (int j = 0; j < AdjList.get(vtx).size(); j++) {
			IntegerTriple v = AdjList.get(vtx).get(j);
		    if (!taken.get(v.second())) {
		    	pq.offer(new IntegerTriple(v.zero(), v.first(), v.second()));  // we sort by weight then by adjacent vertex
		    }
		}
	}

}

class IntegerTriple implements Comparable<IntegerTriple> {
	  public int _zero, _first, _second; //weight, vertex, neighbor

	  public IntegerTriple(int w, int u, int v) {
		_zero = w; 
	    _first = u;
	    _second = v;
	  }

	  public int compareTo(IntegerTriple o) {
	    if (!(this.zero() == o.zero()))	//compare weight
	    	return this.zero() - o.zero();
	    else if (!(this.first() == o.first()))
	    	return this.first() - o.first();
	    else
	    	return this.second() - o.second();
	  }

	  int first() { return _first; }

	  int second() { return _second; }
	  
	  int zero() { return _zero; }
	}
