//Name: Ong Yi Fan
//Matric No.: A0155188W

package Lab2;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//Details: Using Java API Sort, including custom comparator for comparing strings lexicographically
// Practice implementation of List ADTs: ArrayList, LinkedList
// Practice creating custom service class

public class sortofsorting {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NameComparator nameComp = new NameComparator();//pointer to object of NameComparator
													   //service class implementation
		while(true) {
			
			int n = Integer.parseInt(sc.nextLine()); // number of inputs
			Student[] arr = new Student[n]; //class Student array
			
			//Exit loop if input = 0
			if (n == 0) {
				break;
				//sc.close();	 // OR can also use this instead of break
				//System.exit(1);// but must comment sc.close below
			}
			
			//Input Name into String array
			for (int i=0; i<n ; i++) {
				String temp = sc.nextLine();
				arr[i] = new Student(temp,temp.substring(0,2));//including idx 0 till idx 1
			}
			
			//Sort Lexicographically
			Arrays.sort(arr,nameComp);
			
			//Print sorted list
			for (Student currStu:arr) {
				System.out.println(currStu);
			}
			System.out.println(" ");
			
		}
		sc.close();
	}

}

//Service Class for Array of Students
class Student {
	public String name;
	public String first2letter;
	
	public Student (String name, String first2letter) {
		this.name = name;
		this.first2letter = first2letter;
	}
	
	public String getname() {return name;}

	public String getfirst2letter() {return first2letter;}
	
	public String toString() {
		return getname();
	}
}

//Comparator for first 2 letter of Student Names
class NameComparator implements Comparator<Student> {

	public int compare(Student s1, Student s2) {
		return s1.getfirst2letter().compareTo(s2.getfirst2letter());
	}

}