package org.my_test.algorithms_book.graph;

/**
 * 边
 */
public class Edge {
    int aVertexIndex;
    int bVertexIndex;

    /**
     * 返回边的其中一个顶点
     */
    public Integer either() {
        return aVertexIndex;
    }

    /**
     * 返回边的另一个顶点
     */
    public Integer other(Integer xVertexIndex) {
        if (this.aVertexIndex == xVertexIndex) return this.bVertexIndex;
        if (this.bVertexIndex == xVertexIndex) return this.aVertexIndex;
        throw new RuntimeException("wrong vertex");
    }

    public Edge(int aVertexIndex, int bVertexIndex) {
        this.aVertexIndex = aVertexIndex;
        this.bVertexIndex = bVertexIndex;
    }
}
