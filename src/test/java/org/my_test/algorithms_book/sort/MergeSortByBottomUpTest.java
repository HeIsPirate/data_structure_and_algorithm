package org.my_test.algorithms_book.sort;

import org.my_test.algorithms_book.sort2022.MergeSortByBottomUp;
import org.testng.annotations.Test;

import java.util.Arrays;

public class MergeSortByBottomUpTest {
    @Test
    public void test() {
        MergeSortByBottomUp<String> mergeSort = new MergeSortByBottomUp<>();
        String[] stringArr = "orgmytestalgorithmsbooksort".split("");
        mergeSort.sort(stringArr);
        System.out.println(Arrays.toString(stringArr));
    }
}
