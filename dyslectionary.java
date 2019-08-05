package week2;
import java.util.*;
import java.io.*;

//sortofsorting + lineup + raggedright
public class dyslectionary {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		while (scan.hasNextLine()) {
			ArrayList<String> words = new ArrayList<>();
			int max = 0;
    
			while (true) {
				String word = scan.nextLine();//scan next line of String input
        
				if (!scan.hasNext()) 
					end = true;
        
				if (word.isEmpty())
					break;
        
				max = word.length() > max ? word.length() : max;
				words.add(word);
        
				if (end)
					break;
				}
    
			//Append words with spaces to match max word length
			for (int i = 0; i < words.size(); i++) {
				String word = words.get(i);
				int spaces = max - word.length();
        
				for (int z = 0; z < spaces; z++)
					word = " " + word;
				words.set(i , word);
			}
    
			//Sort words from back to front
			Collections.sort(words , new Comparator<String>() {//comparator defined in main
				public int compare(String str1 , String str2) {
					for (int i = str1.length() - 1; i >= 0; i--) {
						if (str1.charAt(i) != str2.charAt(i)) {
							return str1.charAt(i) - str2.charAt(i);
						}
					}
					return 0;
				}
			});
    
			//Print Output
			for (String word : words)
				System.out.println(word);
    
			if (!end)
				System.out.println();
		}

		scan.close();
    }
}
