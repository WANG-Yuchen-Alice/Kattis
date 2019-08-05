package week1;
import java.util.Scanner;
//Revise 2D character array, recursive function
public class treasurehunt {
	public static void main(String[] args) {
		Scanner scanned = new Scanner(System.in);
		
		int r = scanned.nextInt(); 
		int c = scanned.nextInt();
		
		char [][] map = new char[r][c]; // map with NSEWT
		String [] input = new String[r]; // string array to input string characters

		for (int i=0; i<r; i++) {
			input[i] = scanned.next(); // input next line of characters(string)
			map[i] = input[i].toCharArray(); // split string characters into columns in one row
		}
		scanned.close();

		int [][] flag = new int[r][c]; // flag to mark out visited
		flag[0][0] = 1; // current starting position (first row, first column) has been visited
		int end = 0; // end=0 -> not end,  end=1 -> found T,  end=-1 -> lost, end=-2 -> out
		
		int[] output = move(r,c,0,0,0,flag,map,end);
		end = output[0];
		int steps = output[1];

		if (end == 1) {
			System.out.println(steps);
		} else if (end == -1) {
			System.out.println("Lost");
		} else if (end == -2) {
			System.out.println("Out");
		}
		
	}
	
	public static int[] move(int r,int c, int x, int y, int steps, int[][] flag, char[][] map, int end) {
		//x, y =  0; location index                     Note: local parameters can be set as attributes
		//steps = 0;									no need to input into method
		flag[x][y] = 1; // current position x,y is visited -> flag

		char character = map[x][y];

		switch (character) {
			case 'N' : x--;
				break;
			case 'S' : x++;
				break;
			case 'E' : y++;
				break;
			case 'W' : y--;
				break;
			case 'T' : end = 1; //exit current recursion of move
				break;
		}

		int [] output = new int[2];
		if (end == 0) {

			if (x<0 || y<0 || x>=r || y>=c) {
				end = -2; // out
				output[0] = end;
				output[1] = steps;
			}else if (flag[x][y] == 1) {
				end = -1; // have been on this space, therefore repeating same path again = lost
				output[0] = end;
				output[1] = steps;
			}else {
				steps++;
				output = move(r,c,x,y,steps,flag,map,end);// recursion
			}
		}else if (end == 1) {
			output[0] = end;
			output[1] = steps;
		}

		return output; //last assignment to output should be in deepest recursion
		//after which no more change to output, just return it
	}

}