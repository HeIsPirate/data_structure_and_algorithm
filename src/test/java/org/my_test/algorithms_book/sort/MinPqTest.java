package org.my_test.algorithms_book.sort;

import org.my_test.algorithms_book.sort2022.MinPq;
import org.testng.annotations.Test;

public class MinPqTest {
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void test() {
        String str = "orgmytestalgorithmsbooksort";
        String[] split = str.split("");

        MinPq<String> minPQ = new MinPq<>(str.length());
        for (String s : split) {
            minPQ.add(s);
            System.out.printf("加入: %s, 当前最小是: %s%n", s, minPQ.getMin());
        }

        for (int i = 0; i < str.length(); i++) {
            String s = minPQ.delMin();
            System.out.println("弹出: " + s);
        }
    }
}
