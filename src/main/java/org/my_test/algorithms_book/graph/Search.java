package org.my_test.algorithms_book.graph;

/**
 * 图的遍历 API
 */
public interface Search<T> {
    /**
     * 找到和起点s连通的所有顶点
     *
     * @param g 图
     * @param s 起点s
     */
    void search(AbstractGraph<T> g, int s);

    /**
     * v和s是连通的吗
     *
     * @param v 顶点v
     * @return 是否连通
     */
    boolean marked(int v);

    /**
     * 与s连通的顶点总数
     *
     * @return 顶点数
     */
    int count();
}