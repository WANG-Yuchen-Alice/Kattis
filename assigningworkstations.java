package Lab5;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
//Difficult ***

//Using Priority Queue to sort Queue w.r.t Custom Comparator
//Details:
//Only 4 cases for each Workstation:
//Case 1: No workstation created yet -> unlock one
//Case 2: Researcher too early, no workstation available currently -> unlock one
//Case 3: Researcher on time -> assigned idle workstation to him
//Case 4: Researcher arrives too late, no unused workstations (either used/locked/none)

//Case 4 is combination of Case 1,2;
//Case 1 == 4, once locked workstations are removed
//Case 2 == 4, researcher too early for unused workstations

//Technically only need Case 1 & 2, which only affects unlocks++

//Note:Priority Queue use Binary Heap, only min. is output when polled, therefore
//order in iterator is still unsorted.

public class assigningworkstations {
    
    public static int n;
    public static int m;
    public static int unlocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());//no. of researchers
        m = Integer.parseInt(st.nextToken());//time of inactivity

        int[][] array = new int[n][2];
        
        //Input WS occupancy & sort by earliest finish occupying time
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken()); // start occupy time
            array[i][1] = Integer.parseInt(st.nextToken()); // occupied duration
        }
        
        //Sort by comparing only first column (arrival time)
        Arrays.sort(array, Comparator.comparingInt(a -> a[0]));

        
        PriorityQueue<WorkStation> pq = new PriorityQueue<WorkStation>(n, new W_Comparator());
        
        for (int i=0; i<n; i++) {//Iterate through all Workstations, start with n WS for n Researchers
            WorkStation ws = new WorkStation(array[i][0], array[i][1]);
            
            //Remove all workstations that are locked, if any
            while (pq.peek() != null && ws.getStart() > pq.peek().getUnused() + m) {
                pq.poll();
            }
            
            //First run of condition checks:
            if (pq.peek() == null) {
                //No workstation at all
                pq.add(ws);
                unlocks++;
            } else if (ws.getStart() < pq.peek().getUnused()) {
                //Researcher too early, no unused currently
                pq.add(ws); 
                unlocks++; 
            } else {// (ws.getStart() <= pq.peek().getUnused() + m && pq.peek().getUnused() <= ws.getStart())
                //Don't need all these conditions because only 2 case
            	//& while loop and above else if condition already remove such scenario
            	//Researcher arrived within unused time & first workstation can be used
                pq.poll(); 
                pq.add(ws); 
            }
        }//end of loop
        
        pw.println(n - unlocks);
        pw.flush();
    }
}

class WorkStation {
    public int starttime;
    public int duration;

    public WorkStation(int starttime, int duration) {
        this.starttime = starttime;
        this.duration = duration;
    }
    public int getUnused() {
        return starttime + duration;
    }
    public int getStart() {
        return starttime;
    }
    public int getDuration() {
        return duration;
    }
}

class W_Comparator implements Comparator<WorkStation> {
    public int compare(WorkStation o1, WorkStation o2) {
        if (o1.getUnused() > o2.getUnused()) {
            return 1;
        } else if (o1.getUnused() < o2.getUnused()) {
            return -1;
        } else {
            return 0;
        }
    }
}
