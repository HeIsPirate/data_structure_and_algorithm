package org.my_test.algorithms_book.graph;

import org.my_test.algorithms_book.sort2022.IndexMinPq;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Prim算法<br>
 * (即时版)
 */
@SuppressWarnings({"DanglingJavadoc", "CommentedOutCode"})
public class PrimMst {
    /**
     * 索引优先队列, 保存的是: <strong>所有的有效横切边中, 非树顶点的最小横切边</strong><br>
     * pq中不会存在`已在树顶点`的边 (将顶点加入树中就会弹出顶点在pq中的唯一边)
     */
    private final IndexMinPq<EdgeWeighted> pq;
    private final boolean[] visited;
    private final EdgeWeighted[] vertexMinEdge;
    private final Double[] vertexMinEdgeWeight;
    private double minWeight;
    private final Queue<EdgeWeighted> edges;

    public PrimMst(EdgeWeightedGraph<Integer> graph) {
        pq = new IndexMinPq<>(graph.vertexNumber);
        edges = new LinkedList<>();
        visited = new boolean[graph.vertexNumber];
        vertexMinEdge = new EdgeWeighted[graph.vertexNumber];
        vertexMinEdgeWeight = new Double[graph.vertexNumber];

        this.addNewVertex(graph, 0);

        while (!pq.isEmpty()) {
            EdgeWeighted minEdge = pq.delMin();

            // pq中都是有效横切边, 因此不需要判断 (在加入pq时已经判断)
            // if (visited[vertexV] && visited[vertexW]) continue;
            minWeight += minEdge.getWeight();
            edges.add(minEdge);

            int vertexV = minEdge.either();
            int vertexW = minEdge.other(vertexV);
            int newVertex = !visited[vertexV] ? vertexV : vertexW;
            this.addNewVertex(graph, newVertex);
        }
    }

    /**
     * 树增加顶点
     *
     * @param vertex 树的新顶点
     */
    private void addNewVertex(EdgeWeightedGraph<Integer> graph, int vertex) {
        for (EdgeWeighted edge : graph.adjEdge(vertex)) {
            int adjVertex = edge.other(vertex);

            /**
             * 如果相邻顶点已访问, 则这个边就是无效边 (因为vertex就是将要加入到树中的顶点)
             * 不需要: visited[vertexV] && visited[vertexW];
             */
            boolean edgeInvalid = visited[adjVertex];
            if (edgeInvalid) {
                // 这一步不需要, 因为adjVertex已加入树中, 则必然已经从pq中弹出adjVertex的唯一边, 所以这边一定不存在
                // 或者说, pq中, 没有`已在树顶点`的边, 只有`有效横切边中非树顶点`的边
                // pq.tryDelete(edge);
                continue;
            }

            Double oldMinEdgeWeight = vertexMinEdgeWeight[adjVertex];

            if (null == oldMinEdgeWeight) {
                vertexMinEdgeWeight[adjVertex] = edge.getWeight();
                vertexMinEdge[adjVertex] = edge;
                pq.insert(edge);
                continue;
            }

            /**
             * 新增树顶点后, `非树顶点`的`最小横切边`可能变小, 因此需要更新pq
             */
            EdgeWeighted oldMinEdge = vertexMinEdge[adjVertex];
            if (oldMinEdgeWeight > edge.getWeight()) {
                vertexMinEdgeWeight[adjVertex] = edge.getWeight();
                vertexMinEdge[adjVertex] = edge;
                pq.changeTo(oldMinEdge, edge);
            }
        }

        visited[vertex] = true;
    }

    @Override
    public String toString() {
        return "{weight: %s, edges: %s}".formatted(this.minWeight, Arrays.toString(edges.toArray()));
    }
}
