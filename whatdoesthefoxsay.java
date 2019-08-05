package week3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class whatdoesthefoxsay {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			String[] recording = br.readLine().split(" ");//split record into sounds array
			
			String temp = br.readLine();
			while(!temp.contains("fox")) {
				st = new StringTokenizer(temp);
				st.nextToken();//<animal>
				st.nextToken();//goes
				String sound = st.nextToken();//<sound>
				if(!hm.containsKey(sound)) {
					hm.put(sound, 1);
				}
				temp = br.readLine();
			}
			
			for(int j=0; j<recording.length; j++) {
				if(!hm.containsKey(recording[j])) {
					pw.print(recording[j]+" ");
				}
			}
			pw.flush();
		}
		

	}

}
