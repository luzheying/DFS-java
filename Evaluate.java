import java.util.*;
import java.util.Stack;
import java.lang.Math;

public class Evaluate {

    public FibonacciMinPQ getDfs(Graph g) {
        FibonacciMinPQ getDfs = new FibonacciMinPQ();
        //TODO
        //get the dfs tree store in fib heap
        int numV = g.getV();
        LinkedList<Integer> a[] = g.getAdj();
        for (int i = 0; i < numV; i++) {
            int key = i;
            getDfs.insert(key);
        }
        return getDfs;
    }

    //TODO
    //f() function
    public double f() {

        return 0;
    }


    public FibonacciMinPQ.Node Evaluate(Graph g) {
        int size = 20;     //after getting dfs
        int num = 1;
        Stack S = new Stack<FibonacciMinPQ.Node>();
        double[] DFN = new double[size];
        double[] LOW = new double[size];
        double[] PARENT = new double[size];
        double[] ST_SIZE = new double[size];
        double[] IMPACT = new double[size];
        double[] CUT_SIZE = new double[size];
        boolean[] COUNTED = new boolean[size];
        FibonacciMinPQ dfs = new FibonacciMinPQ();
        ArrayList<FibonacciMinPQ.Node> removed = new ArrayList<>();
        FibonacciMinPQ.Node vertex = dfs.new Node();
        Evaluate E = new Evaluate();
        dfs = E.getDfs(g);
        S.push(dfs.getHead());
        int count = 1;
        while (count != size) {
            vertex = (FibonacciMinPQ.Node) S.peek();
            FibonacciMinPQ.Node y = dfs.new Node();
            FibonacciMinPQ.Node x = dfs.new Node();
            int flag = 0;
            while (vertex.next != null) {
                if (!vertex.next.visited) {
                    vertex.next.visited = true;
                    y = vertex.next;
                    flag = 1;
                    count++;
                    break;
                }
                x = vertex;
                vertex = vertex.next;
            }
            if (flag == 1) {
                S.push(y);
                DFN[y.order] = num;
                LOW[y.order] = DFN[y.order];
                PARENT[y.order] = x.order;
                ST_SIZE[y.order] = 1;
                IMPACT[y.order] = 0;
                num++;
            } else {
                S.pop();
                removed.add(vertex);
                FibonacciMinPQ.Node temp = dfs.new Node();
                FibonacciMinPQ.Node comp = dfs.new Node();
                FibonacciMinPQ.Node w = dfs.new Node();
                temp = vertex;

                while (vertex.next != null) {
                    w = vertex.next;
                    if (DFN[comp.order] < DFN[w.order] && PARENT[comp.order] != y.order) {
                        LOW[comp.order] = Math.min(LOW[comp.order], DFN[comp.order]);
                    } else {
                        LOW[comp.order] = Math.min(LOW[comp.order], LOW[w.order]);
                        if (!COUNTED[w.order] && (PARENT[comp.order] != w.order || comp == dfs.getHead())) {
                            COUNTED[w.order] = true;
                            ST_SIZE[comp.order] += ST_SIZE[w.order];
                        }
                        if (LOW[w.order] >= DFN[vertex.order] && vertex != dfs.getHead()) {
                            comp.articulation = true;
                            CUT_SIZE[comp.order] += CUT_SIZE[w.order];
//                            IMPACT[comp.order] += f(ST_SIZE[w.order])
                        }
                    }
                    if (comp == dfs.getHead() && (comp.child.prev != null || comp.child.next != null)) {
                        comp.articulation = true;
                    }
                    vertex = vertex.next;
                }

                while (temp.prev != null) {
                    w = temp.prev;
                    if (DFN[comp.order] < DFN[w.order] && PARENT[comp.order] != y.order) {
                        LOW[comp.order] = Math.min(LOW[comp.order], DFN[comp.order]);
                    } else {
                        LOW[comp.order] = Math.min(LOW[comp.order], LOW[w.order]);
                        if (!COUNTED[w.order] && (PARENT[comp.order] != w.order || comp == dfs.getHead())) {
                            COUNTED[w.order] = true;
                            ST_SIZE[comp.order] += ST_SIZE[w.order];
                        }
                        if (LOW[w.order] >= DFN[vertex.order] && vertex != dfs.getHead()) {
                            comp.articulation = true;
                            CUT_SIZE[comp.order] += CUT_SIZE[w.order];
//                            IMPACT[comp.order] += f(ST_SIZE[w.order])
                        }
                    }
                    if (comp == dfs.getHead() && (comp.child.prev != null || comp.child.next != null)) {
                        comp.articulation = true;
                    }
                    temp = temp.prev;
                }
            }
        }
        FibonacciMinPQ.Node v = dfs.new Node();
        FibonacciMinPQ.Node root = dfs.new Node();
        root = dfs.getHead();
        //TODO iterator
        double temp = IMPACT[root.order];
        if (root.articulation) {
//                IMPACT[root.order] += f(num - CUT_SIZE[root.order]);
        } else {
//                IMPACT[root.order] += f(num - 1);
        }
        if (temp < IMPACT[root.order]) {
            v = root;
//                break;
        }
        return v;

    }
}
