package org.my_test.algorithms_book.graph;

import org.my_test.algorithms_book.dynamic_connectivity.AbstractUnionFind;
import org.my_test.algorithms_book.dynamic_connectivity.QuickFind;
import org.my_test.algorithms_book.sort2022.MinPq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html">来自书上的</a>
 */
@SuppressWarnings({"FieldMayBeFinal", "StringEquality"})
public class PrimMstLazilyFromBook {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;       // total weight of MST
    private Queue<EdgeWeighted> mst;     // edges in the MST
    private boolean[] marked;    // marked[v] = true iff v on tree
    private MinPq<EdgeWeighted> pq;      // edges with one endpoint in tree

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     *
     * @param G the edge-weighted graph
     */
    public PrimMstLazilyFromBook(EdgeWeightedGraph<Integer> G) {
        mst = new LinkedList<>();
        pq = new MinPq<>(G.edgeNumber);
        marked = new boolean[G.vertexNumber];
        for (int v = 0; v < G.vertexNumber; v++)     // run Prim from all vertices to
            if (!marked[v]) prim(G, v);     // get a minimum spanning forest

        // check optimality conditions
        assert check(G);
    }

    // run Prim's algorithm
    private void prim(EdgeWeightedGraph<Integer> G, int s) {
        scan(G, s);
        while (!pq.isEmpty()) {                        // better to stop when mst has V-1 edges
            EdgeWeighted e = pq.delMin();                      // smallest edge on pq
            int v = e.either(), w = e.other(v);        // two endpoints
            assert marked[v] || marked[w];
            if (marked[v] && marked[w]) continue;      // lazy, both v and w already scanned
            mst.add(e);                            // add e to MST
            weight += e.getWeight();
            if (!marked[v]) scan(G, v);               // v becomes part of tree
            if (!marked[w]) scan(G, w);               // w becomes part of tree
        }
    }

    // add all edges e incident to v onto pq if the other endpoint has not yet been scanned
    private void scan(EdgeWeightedGraph<Integer> G, int v) {
        assert !marked[v];
        marked[v] = true;
        for (EdgeWeighted e : G.adjEdge(v))
            if (!marked[e.other(v)]) pq.add(e);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     *
     * @return the edges in a minimum spanning tree (or forest) as
     * an iterable of edges
     */
    public Iterable<EdgeWeighted> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     *
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph<Integer> G) {

        // check weight
        double totalWeight = 0.0;
        for (EdgeWeighted e : edges()) {
            totalWeight += e.getWeight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        AbstractUnionFind uf = new QuickFind(G.vertexArray);
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (EdgeWeighted e : edges()) {

            // all edges in MST except e
            uf = new QuickFind(G.vertexArray);
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (EdgeWeighted f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.getWeight() < e.getWeight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}
