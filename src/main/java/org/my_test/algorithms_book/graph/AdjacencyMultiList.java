package org.my_test.algorithms_book.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 邻接多重表
 */
public class AdjacencyMultiList extends AbstractGraph<Integer> {
    /**
     * `顶点边列表`数组
     */
    private final List<Edge>[] vertexEdgeArray;

    @SuppressWarnings("unchecked")
    public AdjacencyMultiList(int vertexNumber) {
        super(vertexNumber);
        vertexEdgeArray = new List[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            vertexEdgeArray[i] = new LinkedList<>();
        }
    }

    @Override
    public void addEdge(int aVertexIndex, int bVertexIndex) {
        Edge edge = new Edge(aVertexIndex, bVertexIndex);
        vertexEdgeArray[aVertexIndex].add(edge);
        vertexEdgeArray[bVertexIndex].add(edge);
    }

    @Override
    public List<Integer> adj(int xVertexIndex) {
        return this.vertexEdgeArray[xVertexIndex].stream().map(edge -> edge.other(xVertexIndex)).toList();
    }
}
