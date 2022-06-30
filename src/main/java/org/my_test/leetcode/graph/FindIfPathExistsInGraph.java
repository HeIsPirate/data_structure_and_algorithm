package org.my_test.leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/find-if-path-exists-in-graph/">寻找图中是否存在路径</a>
 */
public class FindIfPathExistsInGraph {
    /**
     * 广度优先搜索
     */
    @SuppressWarnings("unchecked")
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination)
            return true;

        // 邻接表
        List<Integer>[] adjacencyArray = new List[n];

        // 构建图
        {
            for (int i = 0; i < n; i++) {
                adjacencyArray[i] = new LinkedList<>();
            }

            for (int[] edge : edges) {
                int vertexV = edge[0];
                int vertexW = edge[1];
                adjacencyArray[vertexV].add(vertexW);
                adjacencyArray[vertexW].add(vertexV);
            }
        }

        // Bfs
        {
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);

            while (!queue.isEmpty()) {
                Integer vertex = queue.poll();

                for (Integer adjVertex : adjacencyArray[vertex]) {
                    if (destination == adjVertex)
                        return true;
                    if (visited[adjVertex])
                        continue;
                    queue.add(adjVertex);
                }

                visited[vertex] = true;
            }
        }

        return false;
    }

    /**
     * 会超时,
     * <a href="https://algs4.cs.princeton.edu/15uf/QuickFindUF.java.html">原理是记录顶点的连通分量</a>
     */
    @Deprecated
    public boolean validPathDeprecated(int n, int[][] edges, int source, int destination) {
        if (source == destination)
            return true;

        // 记录顶点的连通分量cc, 并初始化
        int[] vertexCc = new int[n];
        for (int i = 0; i < vertexCc.length; i++) {
            vertexCc[i] = i;
        }

        for (int[] edge : edges) {
            int vertexV = edge[0];
            int vertexW = edge[1];
            int vCc = vertexCc[vertexV];
            int wCc = vertexCc[vertexW];

            if (vCc == wCc) {
                continue;
            }

            // 更新连通分量
            for (int j = 0; j < vertexCc.length; j++) {
                if (vertexCc[j] == vCc)
                    vertexCc[j] = wCc;
                if (vertexCc[source] == vertexCc[destination])
                    return true;
            }
        }

        return false;
    }
}