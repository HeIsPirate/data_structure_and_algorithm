package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class AdjacencyListTest {

    @Test
    public void test() throws IOException {
        AdjacencyList<Integer> adjacencyList = BUILD_BY_MEDIUM_G();
        System.out.println(adjacencyList);
    }

    public static AdjacencyList<Integer> BUILD_BY_MEDIUM_G() {
        AtomicReference<AdjacencyList<Integer>> adjacencyList = new AtomicReference<>();
        GraphTestUtil.readMediumGraph(vertexNum -> {
            adjacencyList.set(new AdjacencyList<>(vertexNum));
            adjacencyList.get().initVertexArray(IntStream.range(0, vertexNum).boxed().toArray(Integer[]::new));
        }, null, (a, b) -> adjacencyList.get().addEdge(a, b));
        return adjacencyList.get();
    }
}
