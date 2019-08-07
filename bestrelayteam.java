package TakeHome1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

//Permutes Best Relay Team configuration

//Details: Creates Custom Comparator to find best team config with fastest time
//Sort by lastleg time first, then iterate across each player, taking their firstleg time +
//fastest to N-th fastest lastleg (without including current iteration's player)
//to find the minimum time taken for the team.

public class bestrelayteam {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		double mintime = 80; //4*(limit)20
		List<Integer> finalteam = new ArrayList<Integer>(4);
		
		Runner[] arr = new Runner[n];
		TimeComparator timeComp = new TimeComparator();
		
		//Storing runners' data
		for (int i=0; i<n ; i++) {
			arr[i] = new Runner(sc.next(), sc.nextDouble(), sc.nextDouble());
		}
		sc.close();
		
		//Sort array w.r.t lastleg time in ascending order
		Arrays.sort(arr,timeComp);
		
		//Select best team
		for (int i=0; i<n ; i++) {
			int teamCount = 1;//count number of runners in team
			List<Integer> tempteam = new ArrayList<Integer>(4);//temp storage for current team config
			double time = arr[i].getfirstleg();//current team time
			tempteam.add(i);//add player index to team

			for (int j=0; (j<n)&&(teamCount<4)  ; j++) {//iterate through other players in sorted list
				//stop if current team config is full
				if (i!=j) {//skip runner if selected to be firstleg 
					time += arr[j].getotherleg();
					tempteam.add(j);
					teamCount++;				
				}
			}
			
			if (mintime > time) {
				mintime = time;//store minimum time
				finalteam = tempteam;//store best team 
			}
		}			
		
		System.out.println(mintime);
		for(int i:finalteam) {
			System.out.println(arr[i].getname());
		}
	}

}

//Service Class for Array of Runners
class Runner {
	public String name;
	public double firstleg;
	public double otherleg;
	
	public Runner (String name, double firstleg, double otherleg) {
		this.name = name;
		this.firstleg = firstleg;
		this.otherleg = otherleg;
	}
	
	public String getname() {return name;}
	
	public double getfirstleg() {return firstleg;}
	
	public double getotherleg() {return otherleg;}
	
	public String toString() {//not necessary
		return name + " " + firstleg + " " + otherleg;
	}

}

//Comparator for other leg timing of class Runners
class TimeComparator implements Comparator<Runner> {

	//method output int, must output int instead of diff. between double parameters
	public int compare(Runner r1, Runner r2) {
		if (r1.getotherleg() < r2.getotherleg()) return -1;
		if (r1.getotherleg() > r2.getotherleg()) return 1;
		return 0;
	}

}
