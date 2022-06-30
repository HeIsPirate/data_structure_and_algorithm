package org.my_test.algorithms_book.sort2022;

import org.my_test.algorithms_book.sort.AbstractSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>快速排序</p>
 * <p>通过辅助数组实现</p>
 */
public class QuickSortBySecondaryArray extends AbstractSort<String> {
    @Override
    public void sort(String[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(String[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        String split = arr[lo];
        List<String> ltArr = new ArrayList<>(); // 辅助数组, 没有通过替换元素实现
        List<String> gtOReqArr = new ArrayList<>();

        for (int i = lo + 1; i <= hi; i++) {
            String each = arr[i];
            if (less(each, split)) {
                ltArr.add(each);
            } else {
                gtOReqArr.add(each);
            }
        }

        for (int i = lo; i <= hi; i++) {
            int subArrIndex = i - lo;
            if (subArrIndex < ltArr.size()) {
                arr[i] = ltArr.get(subArrIndex);
            } else if (subArrIndex == ltArr.size()) {
                arr[i] = split;
            } else {
                arr[i] = gtOReqArr.get(subArrIndex - ltArr.size() - 1);
            }
        }

        sort(arr, lo, lo + ltArr.size() - 1);
        sort(arr, lo + ltArr.size() + 1, hi);
    }

    public static void main(String[] args) {
        String[] arr = "smttytksoytzege".split("");

        new QuickSortBySecondaryArray().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
