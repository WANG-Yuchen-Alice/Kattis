package TakeHome3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

//Suppose to use AVL tree to sort & get rank of Team 1

//Instead, Hash Set used to keep track of Team ahead in rank of Team 1
//Update Scores for each Team (m iterations)
//Add if current Team risen ahead of Team 1, rank of Team 1 reduces for each insert
//If current Team is Team 1, re-evaluate rank of Team 1 in comparison to others (Score Change)
//Remove Teams who had fallen behind Team 1 by iterating through Hash Set

public class gcpc {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());//no. of teams
        int m = Integer.parseInt(st.nextToken());//no. of events
        
        //Array to maintain addresses of each team
        Team[] order = new Team[n];
        for(int i=0; i<n; i++) {
        	Team curr = new Team(i+1);
        	order[i] = curr;
        }
        
        //Hash Set to only hash teams who are in front of Team 1
        HashSet<Team> hs = new HashSet<Team>();
        T_Comparator judge = new T_Comparator();
        
        //Iterate through each event
        for(int i=0; i<m; i++) {
        	
        	st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());//Team no.
            int p = Integer.parseInt(st.nextToken());//Penalty
            
            //Update Score
        	order[t-1].addPen(p);
        	order[t-1].solvedProb();
        	
        	//Current Team is not Team 1
        	if(t != 1) {
        		if(!hs.contains(order[t-1])) {//Current Team is behind of Team 1
        			if(judge.compare(order[t-1], order[0]) < 0) {//Score better
        				hs.add(order[t-1]);
        			}
        		}
        	}else {
        	//Current Team is 1 (must only run this when Team 1, else O(n) for all iterations -> O(n*m) = TLE
        		//Update Ranking for other Teams; remove Teams if rank < rank_Team_1
        		Iterator<Team> it = hs.iterator();
        		while(it.hasNext()) {
        			Team curr = it.next();
        			if((!curr.equals(order[0]))&&(judge.compare(curr, order[0]) > 0)) {//Score worse
        				it.remove();
        			}
        		}
        	}
        	
        	//No. of teams that scores better than Team 1 = Team 1's rank
        	int idx = hs.size() + 1;
        	pw.println(idx);
        	
        }
        pw.flush();
	}
}

class Team {
	private int name;
	private int problem;//problem solved
	private int penalty;
	public Team(int name) {
		this.name = name;
		problem = 0;
		penalty = 0;
	}
	public int getName() {
		return name;
	}
	public int getProb() {
		return problem;
	}
	public int solvedProb() {
		return problem++;
	}
	public int getPen() {
		return penalty;
	}
	public void addPen(int dmg) {
		penalty += dmg;
	}
}

class T_Comparator implements Comparator<Team> {
	
	public int compare(Team o1, Team o2) {
		if(o1.getProb()>o2.getProb()) {//higher solved problem is on the left
			return -1;
		}else if (o1.getProb()<o2.getProb())
			return 1;
		else {
			if(o1.getPen()<o2.getPen()) {//lower penalty is on the left
				return -1;
			}else if(o1.getPen()>o2.getPen()) {
				return 1;
			}else {
				if(o1.getName()<=o2.getName()){//number 1 will always be on the left
					return -1;
				}else {
					return 1;
				}
			}
		}
	}
}
