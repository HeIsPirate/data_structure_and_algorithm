package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

import java.util.Arrays;

public class DfsTest {
    @Test
    public void testByAdjacencyList() {
        AdjacencyList<Integer> adjacencyList = AdjacencyListTest.BUILD_BY_MEDIUM_G();

        Dfs<Integer> dfs = new Dfs<>();
        dfs.search(adjacencyList, 1);
        System.out.println(Arrays.toString(dfs.pathTree));
        System.out.println(dfs.count());
    }
}