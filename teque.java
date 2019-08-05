package TakeHome2;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

//Use Custom Dequeue (Double-ended Queue) to implement Teque (Triple-ended Queue)
//Details:
//Arrays are used to achieve O(1) in get values (therefore LL cannot be used)
//Since there is no remove operation, there is no need to maintain continuity between array cells
//To remove the high complexity in shifting elements in the array when 
//pushing values in the middle, 2 Dequeue Arrays are implemented 
//Insertion starts from the center, so element can be added from the left or right
//Front & Back pointers are used to track boundary of values

//Better solution: instead of using huge array, can use express train analogy (N Linked List; problem with get value)
//Split N stations into (N)^1/2 express stops, each express station skips (N)^1/2 stations
//Access i-th express station, which points to the i*(N)^1/2-th station in the array
//Therefore, travelling anywhere is at most O(N^1/2)

public class teque {
	
	private static Dequeue One = new Dequeue();//index 0 to mid-1
	private static Dequeue Two = new Dequeue();//index mid to size
	
	private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			String operation = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			compute(operation,num);
		}
		pw.flush();
	}
	
	public static void compute(String op, Integer n) {//compute operation
		
		int totalsize = One.getSize() + Two.getSize();
		
		if(totalsize == 0) {//create first node
			Two.addFirst(n);
		} else {
		
		switch(op) {
		case "push_front":
			if (One.empty()) {
				One.addFirst(n);
			}else {
				One.push_front(n);
				balance();
			}
			break;
			
		case "push_middle":
			if (totalsize == 1) {
				Two.push_back(n);
			}else {
				if (totalsize%2 == 0) {//even
					Two.push_front(n);
				}else {//odd
					One.push_back(Two.pop_front());
					Two.push_front(n);
				}
			}
			balance();
			break;
			
		case "push_back":
			Two.push_back(n);
			balance();
			break;
			
		case "get":
			int Front = One.getFront();
			int Mid = Two.getFront();
			
			int idx;
			if(n+1 <= One.getSize()) {//number in array One; convert to 1-index
				idx = n + Front;
				pw.println(One.getNumber(idx));
			}else {//number in array Two
				idx = n - One.getSize() + Mid;
				pw.println(Two.getNumber(idx));
			}
			break;
		}
		}
	}
	
	public static void balance() { //balance size of dequeue One & Two, s.t. adhere to indexing
		if ((One.getSize() == Two.getSize())||(One.getSize()+1 == Two.getSize())) {//balanced
			//even								//odd
			return;
		}else {//unbalanced	
			if(One.empty()) {
				One.addFirst(Two.pop_front());
			}else if (One.getSize()>Two.getSize()){
				Two.push_front(One.pop_back());
			}else if (One.getSize()<Two.getSize()) {
				One.push_back(Two.pop_front());
			}
		}
		return;
	}
}

interface QueueADT {
	//return true if no elements in queue
	public boolean empty();
	//insert element into front of queue
	public void push_front(int element);
	//remove element from back of queue
	public int pop_front();
	//insert element into back of queue
	public void push_back(int element);
	//remove element from back of queue
	public int pop_back();
}

class Dequeue implements QueueADT{
	public int[] arr;
	public int front;
	public int back;
	public int size;
	private int MAX = 1000000;
	//Constructors
	public Dequeue() {
		arr = new int[MAX]; //10^6
		front = -1;
		back = -1;
		size = 0;
	}
	//Methods
	public void addFirst(int number) { //First Push
		front = MAX/2;
		back = MAX/2;
		arr[front] = number; 
		size = 1;
	}
	public boolean empty() {
		if ((front == -1)||(back == -1)){
			return true;
		}else {
			return false;
		}
	}
	public void push_front(int number) {
		front--;
		arr[front] = number;
		size++;
	}
	public int pop_front() {
		int number = arr[front];
		front++;
		size--;
		return number;
	}
	public void push_back(int number) {
		back++;
		arr[back] = number;
		size++;
	}
	public int pop_back() {
		int number = arr[back];
		back--;
		size--;
		return number;
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
	public int getNumber(int idx) {
		return arr[idx];
	}
}
