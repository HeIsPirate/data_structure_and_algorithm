package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

public class EdgeWeightedGraphTest {
    @Test
    public void test() {
        EdgeWeightedGraph<Integer> graph = GraphTestUtil.BUILD_BY_MEDIUM_EWG();
        System.out.println(graph);
        System.out.println(graph.adjEdge(72));
    }
}
