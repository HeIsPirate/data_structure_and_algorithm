package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class AdjacencyMatrixTest {
    @Test
    public void test() {
        AdjacencyMatrix<Integer> adjacencyMatrix = BUILD_BY_MEDIUM_G();
        System.out.println(adjacencyMatrix);
    }

    public static AdjacencyMatrix<Integer> BUILD_BY_MEDIUM_G() {
        AtomicReference<AdjacencyMatrix<Integer>> adjacencyMatrix = new AtomicReference<>();
        GraphTestUtil.readMediumGraph(vertexNum -> {
            adjacencyMatrix.set(new AdjacencyMatrix<>(vertexNum));
            adjacencyMatrix.get().initVertexArray(IntStream.range(0, vertexNum).boxed().toArray(Integer[]::new));
        }, null, (a, b) -> adjacencyMatrix.get().addEdge(a, b));
        return adjacencyMatrix.get();
    }
}
