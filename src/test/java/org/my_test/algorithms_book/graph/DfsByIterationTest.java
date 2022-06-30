package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

public class DfsByIterationTest {
    @Test
    public void test() {
        AdjacencyList<Integer> graph = AdjacencyListTest.BUILD_BY_MEDIUM_G();

        DfsByIteration<Integer> dfs = new DfsByIteration<>();
        dfs.search(graph, 1);
    }
}
