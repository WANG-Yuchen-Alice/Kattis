package week5;

import java.util.*;
import java.io.*;

public class traveltheskies {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        //No. of airports
        int k = Integer.parseInt(st.nextToken());
        //No. of days of the flight departure window 
        int n = Integer.parseInt(st.nextToken());
        //No. of flights in the window
        int m = Integer.parseInt(st.nextToken());
        
        long[][] airports = new long[n+1][k];//each row - 1 day, each column - 1 airport
        HashMap<Integer,LinkedList<Flight>> hm =  new HashMap<Integer,LinkedList<Flight>>();
        
        for(int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken()) - 1;//departure location
        	int v = Integer.parseInt(st.nextToken()) - 1;//arrival location
        	int d = Integer.parseInt(st.nextToken()) - 1;//day of departure
        	long z = Integer.parseInt(st.nextToken());//capacity of flight
        	
        	if(hm.containsKey(d)) {
        		hm.get(d).add(new Flight(u,v,d,z));
        	}else{
        		hm.put(d, new LinkedList<Flight>());
        		hm.get(d).add(new Flight(u,v,d,z));
        	}
        }
 
        for(int i=0; i<k*n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken()) - 1;//airport
        	int b = Integer.parseInt(st.nextToken()) - 1;//day
        	int c = Integer.parseInt(st.nextToken());//no. of customers departing at airport a on day b
        	
        	airports[b][a] = c;//add incoming departing customers
        }
        
        //Simulate each day
        for(int i=0; i<n; i++) {
        	Queue<Flight> curr = hm.get(i);//day i
        	if(curr != null){
        		while(!curr.isEmpty()) {
        			Flight f = curr.poll();
                	if(f.capacity <= airports[f.day][f.u]) {//more than capacity
                		airports[f.day+1][f.v] += f.capacity;//+ customers arriving to dest. airport ("next" day)
                		airports[f.day][f.u] -= f.capacity;//- customers leaving from depart. airport
                	}else {
                		airports[f.day+1][f.v] += airports[f.day][f.u];//+ customers arriving to dest. airport  ("next" day)
                		airports[f.day][f.u] = 0;// - customers leaving from depart. airport
                		pw.println("suboptimal");
                		pw.flush();
                		return;
                	}
        		}
        	}
        	for(int j=0; j<k; j++) {
        		airports[i+1][j] += airports[i][j];//+ leftover customers who stay the night to next day
        	}
        }
        pw.println("optimal");
        pw.flush();	
    }
}

class Flight {
	public int u;
	public int v;
	public int day;
	public long capacity;
	Flight(int u, int v, int d, long z){
		this.u = u;
		this.v = v;
		day = d;
		capacity = z;
	}
}