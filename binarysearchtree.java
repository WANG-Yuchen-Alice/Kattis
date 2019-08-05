package week4;

import java.util.*;
import java.io.*;

//Note: cannot just replicate bst, because when given worst case (insert in asecending/descending order)
//Search will take O(N), & for N Searches O(N^2)

public class binarysearchtree {
    
    public static TreeSet<Node> ts = new TreeSet<Node>();
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        int N = Integer.parseInt(br.readLine());
        
        //Create root of BST
        long C = 0;//**#$%^$%$#
        int Y = Integer.parseInt(br.readLine());
        Node root = new Node(Y, 0);
        ts.add(root);
        pw.println(root.depth);
        
        //Insert new node
        for(int i=1; i<N ; i++) {
            Y = Integer.parseInt(br.readLine());
            Node node = new Node(Y,0);
            Node successor = ts.ceiling(node);//successor
            Node predecessor = ts.floor(node);
 
            //Case 1: only predecessor
            if(successor == null) {//no successor = confirm predecessor
                predecessor.setRight(node);
                node.setDepth(predecessor.depth + 1);
            //Case 2: only successor
            } else if(predecessor == null){//no predecessor = confirm successor
                successor.setLeft(node);
                node.setDepth(successor.depth + 1); 
            //Case 3: successor + predecessor; decide which one is leaf
            } else if(successor.left == null) {//successor has no left child
                successor.setLeft(node);
                node.setDepth(successor.depth + 1);     
            } else if(predecessor.right == null) {//predecessor has no right child
                predecessor.setRight(node);
                node.setDepth(predecessor.depth + 1);
            }
            ts.add(node);
            C += node.depth;
            pw.println(C);
        }
        pw.flush();
    }
}

class Node implements Comparable <Node>{
    public int key;
    public int depth;
    public Node left;
    public Node right;
    
    public Node(int num, int d) {
        key = num;
        depth = d;
    }
    
    public void setLeft(Node n) {
        left = n;
    }
    public void setRight(Node n) {
        right = n;
    }
    public void setDepth(int d) {
        depth = d;
    }
    public int compareTo(Node o) {
        return this.key - o.key;
    }
}
