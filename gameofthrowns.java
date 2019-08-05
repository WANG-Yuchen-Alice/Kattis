package week3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class gameofthrowns {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());//no. of students
		int k = Integer.parseInt(st.nextToken());//no. of commands
		
		st = new StringTokenizer(br.readLine());
		
		Stack<Integer> moves = new Stack<Integer>();
		moves.push(0);//first child 0
		
		while(st.hasMoreTokens()) {
			String cmd = st.nextToken();
			if (cmd.equals("undo")) { //cmd is undo
				int num = Integer.parseInt(st.nextToken());
				for(int j=0; j<num; j++) {
					moves.pop();
				}
			}else {	
				int child = Integer.parseInt(cmd) + moves.peek();
				if (child < 0) {//negative steps which moves to child beyond 0 to n-1
					while(child<0) {
						child += n;
					}
				}else {//positive steps
					child = child%n;
				}
				moves.push(child);
			}
		}
		
		System.out.println(moves.peek());

	}
}
