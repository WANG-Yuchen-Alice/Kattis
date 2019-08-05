package Lab7;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

//Using basic Graph DS (Adjacency List) to determine vertices that are not part of a triangle
//Details:
//Scan through each list(row) of adjacency list to see the neighbors connected to current vertex
//Different permutations of neighbor J & K are considered in the current vertex's list
//Check in the neighbor J or K's list if it contains its counterpart (K or J respectively)
//If true, current vertex, J & K forms a triangle
//Note: vertex list with only 1 neighbor is impossible to form a triangle

public class weakvertices {

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        //Number of vertices in current test case
        int N = Integer.parseInt(br.readLine());
        
        while(N != -1) {
        
        	//Initialize adjacency list
        	ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        
        	for(int i=0; i<N ; i++) {
        		st = new StringTokenizer(br.readLine());
        		ArrayList<Integer> curr = new ArrayList<Integer>();
        		for(int j=0; j<N ; j++) {
        			int num = Integer.parseInt(st.nextToken());
        			if (num == 1) {//record neighbors
        				curr.add(j);//neighbor index
        			}
        		}
        		adjList.add(curr);
        	}
        	
        	
        	//Find Triangle
        	int[] Triangle = new int[N];
        	
        	for(int i=0; i<N ; i++) {
        		ArrayList<Integer> currV = adjList.get(i);//current vertex's neighbor list in array list
        		if(currV.size() >= 2) {//connected to more than 2 neighbors (then can form triangle)
        			for(int j=0; j<currV.size() ; j++) { 
        				int n1 = currV.get(j);//first neighbor J
        				int n2 = -1;//dummy neighbor
        				//Note: must use nested loop to scan through current vertex's list
        				//Cannot just scan next in the list (missing out on some permutations of J,K)
        				for(int k=0; k<currV.size(); k++) {
        					if(k!=j)//2 different neighbors
        						n2 = currV.get(k);//second neighbor K; 
        					if(adjList.get(n1).contains(n2)) {
            					Triangle[i] = 1;//only track those that form a triangle
            					Triangle[n1] = 1;//flag them in array
            					Triangle[n2] = 1;
            				}
        				}
        			}
        		}        			
        	}
        	
        	for(int i=0; i<N ;i++) {
        		if(Triangle[i] == 0)//not part of a triangle
        			pw.print(i + " ");
        	}
        
        	N = Integer.parseInt(br.readLine());
        	pw.println();
        }
        pw.flush();
        br.close();
	}
}
