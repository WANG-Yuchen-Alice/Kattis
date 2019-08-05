package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class integerlists {
	
	private static String[] list;
	private static Dequeue dq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int T = Integer.parseInt(br.readLine());//no. of test cases
		
		//Test Cases
		for(int i=0; i<T; i++) {
			dq = new Dequeue();
			
			//Input functions R,D
			char[] functions = br.readLine().toCharArray();
			
			//Input no. of integers
			int n = Integer.parseInt(br.readLine());
			
			String input = br.readLine();
			
			if(n != 0) { //[ ]  has number(s) inside
				input = input.substring(1, input.length()-1);//remove [ ]
				list = input.split(",");//list of integers
				dq.addAll(list);
			}
			
			//Performs 'R' 'D' operations
			int flag = 1;
			for(int j=0; j<functions.length; j++) {
				flag = compute(functions[j]);
				if(flag < 0){//error
					break;
				}
			}
			
			//Print Output
			if(flag == -1){
				pw.println("error");
			}else if(flag == 0){
				pw.println("[]");
			}else {
				pw.print("[");
				if(dq.getReverse()) {//reverse
					pw.print(dq.pop_back());
					while(!dq.empty()) {
						pw.print(",");
						pw.print(dq.pop_back());
					}
				}else {//normal
					pw.print(dq.pop_front());
					while(!dq.empty()) {
						pw.print(",");
						pw.print(dq.pop_front());
					}
				}
				pw.println("]");
			}					
		}
		//end of test cases
		pw.flush();
	}
	
	public static int compute(char function) {

		if(dq.empty()) {
			if(function == 'D')
				return -1;//error
			else
				return 0;//R empty []
		}else {
			switch(function){
				case 'R'://Reverses order of integers
					dq.reverse();
					break;
				case 'D'://Drops first integer
					if(dq.getReverse()) {//reverse order
						dq.pop_back();
					}else {//normal order
						dq.pop_front();
					}
					if(dq.empty())//final check, if dropping last int
						return 0;
					break;
			}	
			return 1;
		}
	}

}

class Dequeue {
	public String[] arr;
	public int front;
	public int back;
	public int size;
	private int MAX = 100000;
	private boolean reverse;
	//Constructors
	public Dequeue() {
		arr = new String[MAX]; 
		front = 0;
		back = -1;
		size = 0;
		reverse = false;
	}
	//Methods
	public void addAll(String[] input) { 
		back = front + input.length - 1;
		size = input.length;
		for(int i=0; i<size; i++) {
			arr[i] = input[i];
		}
	}
	public boolean empty() {
		if (size == 0){
			return true;
		}else {
			return false;
		}
	}
	public String pop_front() {
		String number = arr[front];
		front++;
		size--;
		return number;
	}
	public String pop_back() {
		String number = arr[back];
		back--;
		size--;
		return number;
	}
	public void reverse() {
		reverse = !reverse;
	}
	public int getFront() {
		return front;
	}	
	public int getBack() {
		return back;
	}
	public int getSize() {
		return size;
	}
	public String getNumber(int idx) {
		return arr[idx];
	}
	public boolean getReverse() {
		return reverse;
	}

}
