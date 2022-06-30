package org.my_test.algorithms_book.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 广度优先遍历
 */
public class Bfs<T> implements Search<T> {
    private boolean[] visited;
    /**
     * 遍历形成的`路径树`, 使用`双亲表示法`
     */
    public int[] pathTree;

    @Override
    public void search(AbstractGraph<T> g, int s) {
        visited = new boolean[g.vertexNumber];
        pathTree = new int[g.vertexNumber];
        Queue<Edge> queue = new LinkedList<>(); // 队列, 队列元素是`边`

        List<Integer> adjList = g.adj(s);
        for (Integer adj : adjList) {
            queue.add(new Edge(s, adj));
        }


        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int v = edge.aVertexIndex;
            int w = edge.bVertexIndex;

            if (visited[v] && visited[w])
                continue;

            // System.out.printf("边: (%s,%s)%n", v, w);
            // 路径树中, v是父结点, w是子结点 (因为是按照这样的顺序遍历的, 所以new Edge()时也是这样放置的)
            pathTree[w] = v;

            visited[v] = true;
            visited[w] = true;

            // 将w的边加入到队列尾部
            adjList = g.adj(w);
            List<Edge> wEdges = adjList.stream().map(adj -> new Edge(w, adj)).toList();
            queue.addAll(wEdges);
        }
    }

    @Override
    public boolean marked(int v) {
        return visited[v];
    }

    @Override
    public int count() {
        int length = 0;
        for (boolean a : visited) {
            if (a)
                length++;
        }
        return length;
    }
}