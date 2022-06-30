package org.my_test.algorithms_book.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 广度优先遍历, 参照书上实现的
 */
@SuppressWarnings("DanglingJavadoc")
public class BfsFromBook<T> implements Search<T> {
    private boolean[] visited;
    private int count;

    @Override
    public void search(AbstractGraph<T> g, int s) {
        visited = new boolean[g.vertexNumber];
        LinkedList<Integer> queue = new LinkedList<>(); // 队列, 队列元素为`顶点`

        visited[s] = true; // while循环里, 第一步会将起点s的相邻顶点标记为已访问, 因此s需要在循环前就打上标记
        queue.addLast(s);

        /**
         * 1. 从队列中弹出顶点
         * 2. 遍历其相邻顶点
         *    1> 将相邻顶点标记为已访问
         *    2> 将相邻顶点加入到队列尾部
         * 3. 继续循环
         */
        while (!queue.isEmpty()) {
            Integer v = queue.pollFirst();
            // 遍历相邻顶点
            List<Integer> adjList = g.adj(v);
            for (Integer w : adjList) {
                if (visited[w])
                    continue;

                // 标记相邻顶点w已访问 (v在之前的循环中已经标记为`已访问`)
                visited[w] = true;
                count++;
                System.out.printf("边: (%s,%s)%n", v, w);

                // 将w加入到队列尾部, 这样若干个while循环后, 循环到w时, 再遍历w的相邻顶点
                queue.addLast(w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return visited[v];
    }

    @Override
    public int count() {
        return count;
    }
}
