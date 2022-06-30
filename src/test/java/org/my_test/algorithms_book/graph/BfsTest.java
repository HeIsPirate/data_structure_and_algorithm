package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

import java.util.Arrays;

public class BfsTest {
    @Test
    public void test() {
        AdjacencyList<Integer> adjacencyList = AdjacencyListTest.BUILD_BY_MEDIUM_G();

        Bfs<Integer> bfs = new Bfs<>();
        bfs.search(adjacencyList, 1);
        System.out.println(Arrays.toString(bfs.pathTree));
    }
}
