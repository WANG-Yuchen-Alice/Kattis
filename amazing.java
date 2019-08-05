package TakeHome4;
import java.util.*;

public class amazing {

    public static Cell[][] maze = new Cell[201][201];
    public static boolean[][] visited = new boolean[201][201];
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        //Starting position
        maze[100][100] = new Cell();
        visited[100][100] = true;
        
        //First move always Up
        System.out.println("up");
        maze[100][100].U = true;
        DFS(99,100,'U');
        
        //Second move: Right
        if(maze[100][100].R == false) {
            System.out.println("right");
            maze[100][100].R = true;
            DFS(100,101,'R');
        }   
        
        //Third move: Down
        if(maze[100][100].D == false) {
            System.out.println("down");
            maze[100][100].D = true;
            DFS(101,100,'D');
        }
        
        //Fourth move: Left
        if(maze[100][100].L == false) {
            System.out.println("left");
            maze[100][100].L = true;
            DFS(100,99,'L');
        }
        
        //Impossible
        System.out.println("no way out");
        sc.nextLine();
        
        sc.close();
    }
    
    //Recursive DFS
    public static void DFS(int x, int y, char move) {
        //Initialize cell
        if(maze[x][y] == null)
            maze[x][y] = new Cell();
        
        //Response
        String res = sc.next();
        
        //Exit program if success
        if(res.equals("solved"))
            System.exit(0);
        
        //Meets Wall
        if(res.contentEquals("wall")) {
            visited[x][y] = false;//unflag to try another directions
            return;
        }
        
        //Up
        if(move != 'D') {//Cannot go up if just came down
            if((maze[x][y].U != true)&&(visited[x-1][y] != true)) {
                System.out.println("up");
                maze[x][y].U = true;
                visited[x-1][y] = true;
                DFS(x-1,y,'U');             
            }       
        }
        
        //Right
        if(move != 'L') {//Cannot go right if just came from left
            if((maze[x][y].R != true)&&(visited[x][y+1] != true)) {
                System.out.println("right");
                maze[x][y].R = true;
                visited[x][y+1] = true;
                DFS(x,y+1,'R');
            }
        }
        
        //Down
        if(move != 'U') {//Cannot go down if just came up
            if((maze[x][y].D != true)&&(visited[x+1][y] != true)) {
                System.out.println("down");
                maze[x][y].D = true;
                visited[x+1][y] = true;
                DFS(x+1,y,'D');
            }
        }
        
        //Left
        if(move != 'R') {//Cannot go left if just came from right
            if((maze[x][y].L != true)&&(visited[x][y-1] != true)) {
                System.out.println("left");
                maze[x][y].L = true;
                visited[x][y-1] = true;
                DFS(x,y-1,'L');
            }
        } 
        
        //Go backwards
        if(move == 'U') {
            System.out.println("down");
            maze[x][y].D = true;
        }else if(move == 'R') {
            System.out.println("left");
            maze[x][y].L = true;
        }else if(move == 'D') {
            System.out.println("up");
            maze[x][y].U = true;
        }else {
            System.out.println("right");
            maze[x][y].R = true;
        }
        sc.next();//receive OK
    }
}


//To track direction traveled at location in maze
class Cell {
    public boolean U;
    public boolean D;
    public boolean L;
    public boolean R;
    
    public Cell() {//No direction move have been made
        U = false;
        D = false;
        L = false;
        R = false;
    }
    
}
