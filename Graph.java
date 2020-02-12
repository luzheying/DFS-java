//package org.arpit.java2blog;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.*;
import java.util.Scanner;


public class Graph
{
    private int count = 0;
    int Vnum;
    Node[] nodelist;
    boolean[] existlist;

    Graph(int Vnum)
    {
        this.Vnum=Vnum;
        this.nodelist = new Node[Vnum];
        for (int i = 0; i <  Vnum; i++) {
            this.nodelist[i] = null;
        }
        this.existlist = new boolean[Vnum];
        for (int i = 0; i < Vnum; i++) {
            this.existlist[i] = false;
        }
    }


    static class Node
    {
        int index;
        boolean visited;
        List<Node> neighbours;
        int score;
        int ancestors;
        int descendants;

        Node(int data)
        {
            this.index=data;
            this.neighbours=new ArrayList<>();
            int score = 0;
            int ancestors = 0;
            int descendants = 0;
        }
        public void addneighbours(Node neighbourNode)
        {
            this.neighbours.add(neighbourNode);
        }
        public List<Node> getNeighbours() {
            return neighbours;
        }
        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }

    // Recursive DFS
    public  void dfs(Node node)
    {
        System.out.print(node.index + " ");
        List<Node> neighbours=node.getNeighbours();
        node.visited=true;
        for (int i = 0; i < neighbours.size(); i++) {
            Node n=neighbours.get(i);
            if(n!=null && !n.visited)
            {
                dfs(n);
            }
            //implement score calculation
            count++;
            n.descendants = count;
            n.ancestors = Vnum -1-count;
            n.score = n.ancestors + comb(n.descendants,2);
        }
    }

    static int comb(int n , int r)
    {
        if( r== 0 || n == r)
            return 1;
        else
            return comb(n-1,r)+comb(n-1,r-1);
    }

    // Iterative DFS using stack
    public  void dfsUsingStack(Node node)
    {
        Stack<Node> stack=new  Stack<Node>();
        stack.add(node);
        while (!stack.isEmpty())
        {
            Node element=stack.pop();
            if(!element.visited)
            {
                System.out.print(element.index + " ");
                element.visited=true;
            }

            List<Node> neighbours=element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n=neighbours.get(i);
                if(n!=null && !n.visited)
                {
                    stack.add(n);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("BarabasiAlbert_n500m1.txt");
        Scanner sc = new Scanner(file);
        Evaluate e = new Evaluate();
        int V = sc.nextInt();
        Graph g = new Graph(V);
        while(sc.hasNext()) {
            String str = sc.nextLine();
            String ver[] = str.split(":");
            int index = Integer.parseInt(ver[0]);
            Node node = new Node(index);
            g.nodelist[index] = node;
            String ad[] = ver[1].split("\\s+");
            for (int i = 0; i < ad.length; i++) {
                Node node_temp = null;
                if (!g.existlist[Integer.parseInt(ad[i])]) {
                    node_temp = new Node(Integer.parseInt(ad[i]));
                    g.nodelist[Integer.parseInt(ad[i])] = node_temp;
                }
                node_temp = g.nodelist[Integer.parseInt(ad[i])];
                node.addneighbours(node_temp);
            }
        }
//        g.DFS(0);

    }
}


