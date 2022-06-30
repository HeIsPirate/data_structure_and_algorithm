package org.my_test.algorithms_book.sort;

import org.my_test.algorithms_book.sort2022.HeapSort;
import org.my_test.algorithms_book.sort2022.HeapSortFromBook;
import org.testng.annotations.Test;

import java.util.Arrays;

public class HeapSortTest {
    @SuppressWarnings({"SpellCheckingInspection"})
    @Test
    public void test() {
        String[] arr = "orgmytestalgorithmbooksor".split("");
        System.out.println(Arrays.stream(arr.clone()).sorted().toList());

        new HeapSort<String>().sort(arr);
        System.out.println(Arrays.toString(arr));

        String[] clone = arr.clone();
        new HeapSortFromBook().sort(clone);
        System.out.println(Arrays.toString(clone));
    }
}
