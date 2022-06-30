package org.my_test.algorithms_book.sort2022;

import org.my_test.algorithms_book.sort.AbstractSort;

import java.util.Arrays;

public class InsertSort extends AbstractSort<String> {
    @Override
    public void sort(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            insertIntoLeft(arr, i);
        }
    }

    private void insertIntoLeft(String[] arr, int itemIndex) {
        String item = arr[itemIndex];
        int insertIndex = 0;

        // 左->右遍历
        for (int i = 0; i <= itemIndex; i++) {
            if (great(item, arr[i])) {
                continue;
            }
            insertIndex = i;
            break;
        }

        // 移动数组
        for (int i = itemIndex; i > insertIndex; i--) {
            arr[i] = arr[i - 1];
        }

        // 插入元素
        arr[insertIndex] = item;
    }

    public static void main(String[] args) {
        String[] arr = "smttytksoytzege".split("");

        new InsertSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
