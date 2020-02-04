// Java program to print DFS traversal from a given given graph
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;

// This class represents a directed graph using adjacency list
// representation

class Graph {
    private int V;   // No. of vertices

    // Array  of lists for Adjacency List Representation
    private LinkedList<Integer> adj[];

    public int getV() {
        return V;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    // Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
            //adj[i].add(i); //the first index of adj is the index of such node
        }
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);  // Add w to v's list.
    }

    // A function used by DFS
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS(int v) {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("BarabasiAlbert_n500m1.txt");
        Scanner sc = new Scanner(file);
        Evaluate e = new Evaluate();
        int nVer = sc.nextInt();
        Graph g = new Graph(nVer);
        while(sc.hasNext()) {
            String str = sc.nextLine();
            String ver[] = str.split(":");
            int index = Integer.parseInt(ver[0]);
            String ad[] = ver[1].split("\\s+");
            for (int i = 0; i < ad.length; i++) {
                g.addEdge(index, Integer.parseInt(ad[i]));
            }
        }
        g.DFS(0);
        

    }
}


