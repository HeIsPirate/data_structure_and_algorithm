package org.my_test.algorithms_book.graph;

import org.testng.annotations.Test;

public class PrimMstLazilyFromBookTest {
    @Test
    public void test() {
        PrimMstLazilyFromBook mst = new PrimMstLazilyFromBook(GraphTestUtil.BUILD_BY_MEDIUM_EWG());
        System.out.println(mst.weight());
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
    }
}
