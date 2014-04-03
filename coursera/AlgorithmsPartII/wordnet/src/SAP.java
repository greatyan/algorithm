import java.util.Arrays;
import java.util.LinkedList;

public class SAP {

    private Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return length(Arrays.asList(v), Arrays.asList(w));
    }

    private class SP {
        private int[] pathes;

        public SP(Digraph g, Iterable<Integer> vs) {
            pathes = new int[g.V()];
            for (int i = 0; i < pathes.length; i++) {
                pathes[i] = -1;
            }

            LinkedList<Integer> queue = new LinkedList<Integer>();
            for (int v : vs) {
                queue.add(v);
                pathes[v] = 0;
            }

            while (!queue.isEmpty()) {
                Integer v1 = queue.removeFirst();
                for (Integer adj : g.adj(v1)) {
                    if (pathes[adj] == -1) {
                        queue.add(adj);
                        pathes[adj] = pathes[v1] + 1;
                    }
                }
            }
        }

        public int getPathLength(int v) {
            return pathes[v];
        }

        public int V() {
            return pathes.length;
        }
    }

    private int getMinLength(SP sp1, SP sp2) {
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < sp1.V(); i++) {
            int len1 = sp1.getPathLength(i);
            int len2 = sp2.getPathLength(i);
            if (len1 != -1 && len2 != -1) {
                if (minLength > len1 + len2) {
                    minLength = len1 + len2;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength;
    }

    private int getMinAncestor(SP sp1, SP sp2) {
        int ancestor = -1;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < sp1.V(); i++) {
            int len1 = sp1.getPathLength(i);
            int len2 = sp2.getPathLength(i);
            if (len1 != -1 && len2 != -1) {
                if (minLength > len1 + len2) {
                    ancestor = i;
                    minLength = len1 + len2;
                }
            }
        }
        return ancestor;
    }

    // a common ancestor of v and w that participates in a shortest ancestral
    // path; -1 if no such path
    public int ancestor(int v, int w) {
        return ancestor(Arrays.asList(v), Arrays.asList(w));
    }

    // length of shortest ancestral path between any vertex in v and any vertex
    // in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        SP sp1 = new SP(graph, v);
        SP sp2 = new SP(graph, w);
        return getMinLength(sp1, sp2);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no
    // such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        SP sp1 = new SP(graph, v);
        SP sp2 = new SP(graph, w);
        return getMinAncestor(sp1, sp2);
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
