package org.my_test.algorithms_book.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 深度优先遍历 (通过迭代实现)
 */
@SuppressWarnings({"DanglingJavadoc", "JavadocBlankLines", "FieldCanBeLocal"})
public class DfsByIteration<T> implements Search<T> {
    private boolean[] visited;

    @Override
    public void search(AbstractGraph<T> g, int s) {
        visited = new boolean[g.vertexNumber];
        /**
         * 将`某个顶点的所有边`整体视为一个栈
         *
         * 举例:
         * 栈0 : 顶点1的边: (1, 0)
         * 栈1 : 顶点1的边: (1, 2)
         * 栈3 : 顶点1的边: (1, 3)
         * 栈4 : 顶点0的边: (0, 2)
         * 栈5 : 顶点0的边: (0, 3)
         * 栈6 : 顶点0的边: (0, 4)
         *
         * `顶点1的边`在`顶点0的边`之上
         */
        LinkedList<Edge> stack = new LinkedList<>();

        /**
         * 过程:
         * 1. 初始化栈: 将顶点0的边入栈
         * 2. 弹出栈顶: 顶点0的第一个边(0, 1)
         * 3. 打印
         * 4. 将`这条边的另一个顶点1`的所有边, 加入栈顶
         * 5. 继续循环, 此时弹出的是顶点1的第一条边了 (实现切换顶点, 即`深度`效果)
         *
         * 有一个先存后取的过程
         */

        // 初始化栈
        List<Integer> adjacencyVertex = g.adj(s);
        for (Integer adj : adjacencyVertex) {
            stack.addLast(new Edge(s, adj));
        }

        while (!stack.isEmpty()) {
            Edge edge = stack.pollFirst();
            int v = edge.aVertexIndex;
            int w = edge.bVertexIndex;

            if (visited[v] && visited[w])
                continue;

            System.out.printf("边: (%s,%s)%n", v, w);

            visited[v] = true;
            visited[w] = true;

            // 将w的边置于栈顶! 这样下次while循环, 会切换到w的边
            adjacencyVertex = g.adj(w);
            for (int i = 0; i < adjacencyVertex.size(); i++) {
                // w的边, 按顺序入栈 (不是直接入栈顶, 这样做只是因为和递归解法执行顺序一致)
                stack.add(i, new Edge(w, adjacencyVertex.get(i)));
            }
        }
    }

    @Override
    public boolean marked(int v) {
        //TODO marked
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
