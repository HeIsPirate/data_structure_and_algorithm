package org.my_test.algorithms_book.graph;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PrimMstLazilyTest {
    private EdgeWeightedGraph<Integer> graph;

    @BeforeTest
    public void beforeTest() {
        this.graph = GraphTestUtil.BUILD_BY_MEDIUM_EWG();
    }

    @Test
    public void test() {
        PrimMstLazily prim = new PrimMstLazily(this.graph);
        System.out.println(prim);
    }
}
