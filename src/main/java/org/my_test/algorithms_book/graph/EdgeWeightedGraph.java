package org.my_test.algorithms_book.graph;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 邻接多重表, 有权重
 */
public class EdgeWeightedGraph<T> extends AbstractGraph<T> {
    private final List<EdgeWeighted>[] vertexEdgeArray;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int vertexNumber) {
        super(vertexNumber);
        this.vertexEdgeArray = new List[vertexNumber];
        for (int i = 0; i < this.vertexEdgeArray.length; i++) {
            this.vertexEdgeArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(EdgeWeighted e) {
        Integer v = e.either();
        Integer w = e.other(v);
        vertexEdgeArray[v].add(e);
        vertexEdgeArray[w].add(e);
    }

    @Override
    public void addEdge(int aVertexIndex, int bVertexIndex) {
        EdgeWeighted edge = new EdgeWeighted(aVertexIndex, bVertexIndex, 0);
        this.addEdge(edge);
    }

    public List<Integer> adj(int v) {
        return this.adjEdge(v).stream().map(e -> e.other(v)).toList();
    }

    public List<EdgeWeighted> adjEdge(int v) {
        return this.vertexEdgeArray[v];
    }

    public List<EdgeWeighted> edges() {
        return Arrays.stream(this.vertexEdgeArray).flatMap(Collection::stream).distinct().toList();
    }

    @Override
    public String toString() {
        return Arrays.toString(this.vertexEdgeArray);
    }
}
