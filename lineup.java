package week2;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

//NEW: Java Collections.sort, Collections.reverse)
public class lineup {

	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		int n = Integer.parseInt(scanned.nextLine());
		LinkedList<Players> list = new LinkedList<Players>();
		LinkedList<Players> forward_list = new LinkedList<Players>();
		LinkedList<Players> reverse_list = new LinkedList<Players>();

		NameComparator nameComp = new NameComparator();
		
		for (int i=0; i<n; i++){
			
			list.add(new Players(scanned.nextLine()));
		}
		scanned.close();
		
		forward_list.addAll(list);
		Collections.sort(forward_list, nameComp);
		
		reverse_list.addAll(forward_list);
		Collections.reverse(reverse_list);
		
		if (list.toString().compareTo(forward_list.toString()) == 0) {//both string equals
			System.out.println("INCREASING");
		} else if (list.toString().compareTo(reverse_list.toString()) == 0) {
			System.out.println("DECREASING");
		} else {
			System.out.println("NEITHER");
		}
	}
}

class Players {
	public String name;
	
	Players(String name) {
		this.name = name;
	}
	
	public String getname() {
		return name;
	}
	
	public String toString() {
		return getname();
	}
}

//comparator 
class NameComparator implements Comparator<Players> {
	public int compare (Players p1, Players p2) {
		return p1.getname().compareTo(p2.getname());
	}
}


