package org.my_test.algorithms_book.graph;

import java.util.Arrays;
import java.util.List;

/**
 * 邻接矩阵 (无向图, 边无权值)
 */
public class AdjacencyMatrix<T> extends AbstractGraph<T> {
    private int[][] matrix;

    public AdjacencyMatrix(int vertexNumber) {
        super(vertexNumber);
        this.matrix = new int[vertexNumber][vertexNumber];
    }

    @Override
    public List<Integer> adj(int xVertexIndex) {
        return Arrays.stream(this.matrix[xVertexIndex]).boxed().toList();
    }

    @Override
    public void addEdge(int aVertexIndex, int bVertexIndex) {
        this.matrix[aVertexIndex][bVertexIndex] = this.matrix[bVertexIndex][aVertexIndex] = 1;
    }

    @Override
    public String toString() {
        String template = "%-4s";
        StringBuilder toString = new StringBuilder();

        toString.append(template.formatted(" "));
        for (T t : this.vertexArray) {
            toString.append(template.formatted(t)).append(" ");
        }


        for (int i = 0; i < this.matrix.length; i++) {
            int[] adjacencyVertex = this.matrix[i];
            toString.append("\n");
            toString.append(template.formatted(i));
            for (int j = 0; j < adjacencyVertex.length; j++) {
                toString.append(template.formatted(adjacencyVertex[j])).append(" ");
            }
        }
        return toString.toString();
    }
}