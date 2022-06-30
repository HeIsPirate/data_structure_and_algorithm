package org.my_test.algorithms_book.graph;

import java.util.List;

/**
 * 深度优先遍历
 * <br>
 * 通过`邻接表`实现
 */
public class Dfs<T> implements Search<T> {
    /**
     * 记录已经访问的顶点
     */
    private boolean[] visited;
    /**
     * 遍历形成的`路径树`, 使用`双亲表示法`
     */
    public int[] pathTree;

    @Override
    public void search(AbstractGraph<T> g, int s) {
        // visited.长度 = 树.长度 = 顶点.长度, 它们三个长度一样, 因此通过下标即可对应
        visited = new boolean[g.vertexNumber];
        pathTree = new int[g.vertexNumber];
        this.dfs(g, s);
    }

    private void dfs(AbstractGraph<T> g, int s) {
        List<Integer> adjList = g.adj(s); // s的所有边
        for (Integer v : adjList) {
            if (visited[v])
                continue;

            // 边是: (s, v)
            // System.out.printf("边: (%s,%s)%n", s, v);
            // 因为是遍历s的所有边, 所以视s为父结点
            // 意思是: 下标v的父结点是s (父结点下标)
            pathTree[v] = s;
            visited[v] = true;

            // 切换顶点v递归
            // (只有v递归完了, 才会继续for循环, 即执行s的下一个相邻顶点)
            this.dfs(g, v);
        }
    }

    @Override
    public boolean marked(int v) {
        // 只要遍历到了, 就必然说明二者是连通的
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