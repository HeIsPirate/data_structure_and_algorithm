package org.my_test.algorithms_book.sort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("DanglingJavadoc")
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {
    @Override
    public void sort(T[] arr) {
        this.sort(arr, 0, arr.length - 1);
    }

    private void sort(T[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partition = partition(arr, lo, hi);
        /**
         * partition位置的元素已经排好序
         * 接下来, 对数组中partition左边的和右边的进行排序(切分)
         * 因为partition位置的元素已经排好序, 因此左边和右边的下标, 都不包含partition位置
         */
        sort(arr, lo, partition - 1);
        sort(arr, partition + 1, hi);
    }

    /**
     * @param arr 完整数组
     * @param lo  对完整数组的部分进行切分, lo是开始下标
     * @param hi  对完整数组的部分进行切分, hi是结束下标
     * @return 切分元素的下标
     */
    private int partition(T[] arr, int lo, int hi) {
        T v = arr[lo];

        int l = lo + 1; // left index
        int r = hi; // right index

        while (true) {
            /**
             * l <= hi 只需要比较 hi~lo 范围, 防止过界
             * arr[l] < v时, 递增l并继续循环, 直到: arr[l] >= v 时跳出循环
             */
            while (l <= hi && less(arr[l], v)) {
                l++;
            }
            while (r >= lo && less(v, arr[r])) {
                r--;
            }

            /**
             * l >= r 表示两下标交换位置了
             * 交换位置的原因是: 遍历完所有的元素了
             * 交换位置的结果是: r(包括r)左边的所有元素, 都<=v
             */
            if (l >= r) {
                exch(arr, lo, r);
                return r;
            }

            exch(arr, l, r);
            l++; // l加一, 比较下一个; 可以优化将两个l++合并; 不执行l++的话, 执行也正确, 但是多比较一次
            r--;
        }
    }

    public static void main(String[] args) {
        char[] before1 = "QUICKSORTEXAMPLE".toCharArray();
        Character[] before = new Character[before1.length];
        for (int i = 0; i < before1.length; i++) {
            before[i] = before1[i];
        }

        System.out.println(Stream.of(before).map(String::valueOf).collect(Collectors.joining(",")));

        new QuickSort<Character>().sort(before);

        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));
    }
}
