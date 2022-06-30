package org.my_test.algorithms_book.sort2022;

import org.my_test.algorithms_book.sort.AbstractSort;

import java.util.Arrays;

/**
 * 二分插入排序<br>
 * (对`插入`操作, 使用`二分法`优化)
 */
public class InsertSortByBinarySearch extends AbstractSort<String> {

    @Override
    public void sort(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            insertIntoLeft(arr, i);
        }
    }

    private void insertIntoLeft(String[] arr, int itemIndex) {
        String item = arr[itemIndex];

        int insertIndex = getInsertIndexByBinary(arr, itemIndex, 0, itemIndex - 1);

        for (int i = itemIndex; i > insertIndex; i--) {
            arr[i] = arr[i - 1];
        }

        arr[insertIndex] = item;
    }

    /**
     * 通过`二分法`找到插入的位置
     */
    private int getInsertIndexByBinary(String[] arr, int itemIndex, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        String item = arr[itemIndex];

        if (hi == lo || hi - lo == 1) {
            return lessOrEquals(item, arr[lo]) ? Math.max(0, lo - 1) /*小于等于0*/
                    : great(item, arr[lo]) && lessOrEquals(item, arr[hi]) ? hi /*大于lo, 小于等于hi*/
                    : great(item, arr[hi]) ? Math.min(arr.length - 1, hi + 1) : -999; /*大于hi*/
        }

        if (less(item, arr[mid])) {
            return getInsertIndexByBinary(arr, itemIndex, lo, mid);
        } else if (great(item, arr[mid])) {
            return getInsertIndexByBinary(arr, itemIndex, mid, hi);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        String[] arr = "smttytksoytzege".split("");

        new InsertSortByBinarySearch().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
