package week3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class grandpabernie {

	public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		HashMap<String,List<Integer>> hm = new HashMap<String,List<Integer>>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String country = st.nextToken();
			int year = Integer.parseInt(st.nextToken());

			if(hm.containsKey(country)) {
				hm.get(country).add(year);
			}else {
				List<Integer> temp = new ArrayList<Integer>();
				temp.add(year);
				hm.put(country, temp);
			}
		}

		for(List<Integer> arr : hm.values()) {
			if (arr.size()>1) {
				Collections.sort(arr);
			}
		}
		
		int q = Integer.parseInt(br.readLine());
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			String country = st.nextToken();
			int times = Integer.parseInt(st.nextToken());
			pw.println(hm.get(country).get(times - 1));
		}
		pw.flush();
	}

}
