package org.my_test.algorithms_book.graph;

import org.my_test.algorithms_book.sort2022.MinPq;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Prim算法<br>
 * MST: 最小生成树 Minimum Spanning Tree<br>
 * (惰性延迟删除无效边)<br>
 */
public class PrimMstLazily {
    /**
     * 优先队列, 用于从横切边中取出最小边
     */
    private final MinPq<EdgeWeighted> pq;
    /**
     * 标记已访问, 也表示树包含的顶点
     */
    private final boolean[] visited;
    /**
     * MST树的边
     */
    private final Queue<EdgeWeighted> edges;
    /**
     * 最小权重
     */
    private double minWeight;

    public PrimMstLazily(EdgeWeightedGraph<Integer> graph) {
        pq = new MinPq<>(graph.edgeNumber);
        visited = new boolean[graph.vertexNumber];
        edges = new LinkedList<>();

        // 选择一个顶点开始, 这里选择顶点0, 然后需要初始化pq
        visitVertex(graph, 0);

        // 终止条件: pq为空
        while (!pq.isEmpty()) {
            // 弹出最小横切边
            EdgeWeighted minEdge = pq.delMin();

            int vertexV = minEdge.either();
            int vertexW = minEdge.other(vertexV);

            // 如果横切边的两个顶点均已在树中, 则该边一定是无效边, 执行continue, 继续从pq中弹出最小横切边, 直到非无效横切边
            if (visited[vertexV] && visited[vertexW]) {
                continue;
            }

            edges.add(minEdge);
            minWeight += minEdge.getWeight();
            int addVertex = !visited[vertexV] ? vertexV : vertexW; // 判断哪个顶点还未加入到树中
            this.visitVertex(graph, addVertex);
        }
    }

    /**
     * 树增加顶点: 标记顶点, 添加新的横切边
     */
    private void visitVertex(EdgeWeightedGraph<Integer> graph, int vertex) {
        // 标记
        visited[vertex] = true;
        // 将新的横切边加入pq !!! `新的横切边`即`新顶点的邻接边`
        for (EdgeWeighted edge : graph.adjEdge(vertex)) {
            if (!visited[edge.other(vertex)]) // 防止重复边被加入到pq中 (当另外一个顶点已访问, 则必然已经将该边添加进pq中)
                pq.add(edge);
        }
    }

    @Override
    public String toString() {
        return "{weight: %s, edges: %s}".formatted(this.minWeight, Arrays.toString(edges.toArray()));
    }
}
