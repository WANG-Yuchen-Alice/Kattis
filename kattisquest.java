package Lab6;

//Use AVL Tree (TreeMap) to sort quest & calculate the amount of gold earned per session
//Details:
//Pick the quest with the largest energy cost that is less than current available energy
//Deduct the energy cost of participating in the quest & sum up gold earned 
//Remove quest from quest list, since completed
//If sufficient energy left, repeat until no sufficient energy remaining or no more quests 
//Output the total amount of gold earn from the quest(s) in current session

//Sorting Order:
//	(1.) highest energy (must be < X to participate in quest) 
//if same energy, then
//	(2.) highest gold reward
//if same gold reward, then
//	(3.) unique identifier (for treemap.remove to differentiate quests with same energy & gold)

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class kattisquest {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	
        //AVL Tree
        TreeSet<IntegerTriple> ts = new TreeSet<IntegerTriple>(new Q_Comparator());
        
        //No. of commands
        int N = Integer.parseInt(br.readLine());
        
		for(int i=0; i<N; i++) {
			//Current command
			st = new StringTokenizer(br.readLine());
			
			switch(st.nextToken()) {
			//Add Quest
			case "add":
				long E = Integer.parseInt(st.nextToken());
				long G = Integer.parseInt(st.nextToken());
				ts.add(new IntegerTriple(E,G,i));
				break;
			//Participate Quest (or at least try to)
			case "query":
				long X = Integer.parseInt(st.nextToken());
				long gold = 0;
				IntegerTriple found = ts.ceiling(new IntegerTriple(X,100001,0));//max gold is 10^5
				while(found != null) {
					gold = gold + found.getGold();
					X = X - found.getEnergy();
					ts.remove(found);
					found = ts.ceiling(new IntegerTriple(X,100001,0));			
				}
				pw.println(gold);
				break;
			}
			
		}
		pw.flush();
	}
}

//Quest
class IntegerTriple {
	private long energy;
	private long gold;
	private int ID;
	public IntegerTriple(long energy, long gold, int ID) {
		this.energy = energy;
		this.gold = gold;
		this.ID = ID;
	}
	public long getEnergy() {
		return energy;
	}
	public long getGold() {
		return gold;
	}
	public int getID() {
		return ID;
	}
}

//TreeMap Comparator to sort in correct order
class Q_Comparator implements Comparator<IntegerTriple>{

	public int compare(IntegerTriple o1, IntegerTriple o2) {
		
		if(o1.getEnergy()>o2.getEnergy()) {
			return -1;
		}else if(o1.getEnergy()<o2.getEnergy()) {
			return 1;
		}else {
			if(o1.getGold()>o2.getGold()) {
				return -1;
			}else if(o1.getGold()<o2.getGold()) {
				return 1;
			}else {
				if(o1.getID()<o2.getID())
					return -1;
				else if(o1.getID()>o2.getID())
					return 1;
				else
					return 0;
			}
		}
	}	
}
