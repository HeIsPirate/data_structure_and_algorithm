package org.my_test.algorithms_book.sort;

import org.my_test.algorithms_book.sort2022.IndexMinPq;
import org.testng.annotations.Test;

public class IndexMinPqTest {
    @Test
    public void test() {
        String str = "orgmytestalgorithmsbooksort";
        String[] split = str.split("");
        // split = Arrays.stream(split).distinct().toArray(String[]::new);

        IndexMinPq<String> pq = new IndexMinPq<>(split.length);

        for (String s : split) {
            pq.insert(s);
        }

        System.out.println(pq);
        pq.change(0, "p");
        System.out.println(pq);
        pq.delete("m");
        System.out.println(pq);


        String min;
        while ((min = pq.delMin()) != null) {
            System.out.print(min);
        }
    }
}
