package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

public class BfsFromBookTest {
    @Test
    public void test() {
        AdjacencyList<Integer> adjacencyList = AdjacencyListTest.BUILD_BY_MEDIUM_G();

        BfsFromBook<Integer> bfs = new BfsFromBook<>();
        bfs.search(adjacencyList, 1);
    }
}
