package org.my_test.algorithms_book.graph;

import java.util.List;

public abstract class AbstractGraph<T> {
    /**
     * 顶点数
     */
    protected int vertexNumber;
    protected int edgeNumber;
    /**
     * 顶点数组
     */
    protected T[] vertexArray;

    /**
     * 初始化
     *
     * @param vertexNumber 顶点数
     */
    @SuppressWarnings("unchecked")
    public AbstractGraph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        vertexArray = (T[]) new Object[vertexNumber];
    }

    /**
     * 添加顶点
     */
    @SuppressWarnings("ManualArrayCopy")
    public void initVertexArray(T[] vertexArray) {
        for (int i = 0; i < vertexArray.length; i++) {
            this.vertexArray[i] = vertexArray[i];
        }
    }

    /**
     * 添加边
     */
    public abstract void addEdge(int aVertexIndex, int bVertexIndex);

    /**
     * 获取该顶点的`所有相邻顶点`
     */
    public abstract List<Integer> adj(int xVertexIndex);

    public void setEdgeNumber(int edgeNumber) {
        this.edgeNumber = edgeNumber;
    }
}