package TakeHome2;
import java.util.*;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

//Use Custom Linked List, to chain node to node O(1),instead of concatenating String
//(strings are immutable, so string 1 + string 2 creates another new string & has to iterate
//across all its characters to add) O(N)
//Array can be used to store index of next string to jump to (but more complicated to implement than LL)

public class joinstrings {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st;
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
		int N = Integer.parseInt(br.readLine());
		
		TailedLinkedList[] str = new TailedLinkedList[N];//Array size N of linked list
		
		for (int i = 0 ; i < N; i++) {//record string into array
			str[i] = new TailedLinkedList(new ListNode(br.readLine()));//linked list only 1 node
		}
		
		if (N == 1) {//edge case, if only 1 string
			System.out.println(str[0].getHead().getItem());
			return;
		}
		
		for (int i = 1; i < N; i++) {//N-1 operations
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			//Append list to another list, according to concat string 
			//(link head of list2 to tail of list1)
			str[a].addLinkedList(str[b]); // O(1)
			
			if (i == N-1) {//last operation
				ListNode temp = str[a].getHead();
				while(temp != null) {
					pw.print(temp.getItem());
					temp = temp.getNext();
				}
			}
		}
		pw.flush();	
	}
}

class ListNode {
	public String item;//for joinstrings
	public Integer number;//for teque
	public ListNode next;
	public ListNode prev; //for teque
	//Constructors
	public ListNode(String value) {
		this.next = null;
		item = value;
	}
	public ListNode(Integer value) {
		this.next = null;
		number = value;
	}
	public ListNode(String value, ListNode next) {
		this.next = next;
		item = value;
	}
	public ListNode(Integer value, ListNode next) {
		this.next = next;
		number = value;
	}
	//Methods
	public String getItem() {
		return item;
	}
	public Integer getNumber() {
		return number;
	}
	public ListNode getNext() {
		return next;
	}
	public ListNode getPrev() {
		return prev;
	}
	public void setItem(String value) {
		item = value;
		return;
	}
	public void setNumber(Integer value) {
		number = value;
		return;
	}
	public void setNext(ListNode next) {
		this.next = next;
		return;
	}	
	public void setPrev(ListNode prev) {
		this.prev = prev;
		return;
	}
}

class TailedLinkedList {
	public ListNode head;
	public ListNode tail;
	//Constructor
	public TailedLinkedList(ListNode One) {
		head = One;
		tail = One;
	}
	//Methods
	public void addLinkedList(TailedLinkedList latter) {
		tail.setNext(latter.getHead());//link nodes
		tail = latter.getTail();
	}
	public ListNode getTail() {
		return tail;
	}
	public ListNode getHead() {
		return head;
	}
}



