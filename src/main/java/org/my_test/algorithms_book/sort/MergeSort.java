package org.my_test.algorithms_book.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {
    @Override
    public void sort(T[] arr) {
        this.sort(arr, 0, arr.length - 1);
    }

    private void sort(T[] arr, int lo /*index*/, int hi /*index*/) {
        if (lo >= hi) return;

        int mid = (hi - lo) / 2 + lo;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);

        merge(arr, lo, mid, hi);
    }

    private void merge(T[] arr, int lo, int mid, int hi) {
        // T[] copy = Arrays.copyOfRange(arr, lo, hi + 1); // 不对, 这样copy.length != arr.length, 只是将lo-hi部分的元素拷贝过去
        T[] copy = Arrays.copyOf(arr, arr.length);

        // lo mid hi 一直都是真实的arr下标! 因为没有将他们重置为0, 都是在原先基础上计算的
        int insert_index = lo; // insert index
        int i = lo; // left index
        int j = mid + 1; // right index

        T min;
        while (i <= mid || j <= hi) {

            T left = i <= mid ? copy[i] : null;
            T right = j <= hi ? copy[j] : null;

            if (i > mid) { // left part end
                min = right;
                j++;
            } else if (j > hi) { // right part end
                min = left;
                i++;
            } else if (less(left, right)) { // left < right
                min = left;
                i++;
            } else { // left >= right
                min = right;
                j++;
            }

            arr[insert_index++] = min;
        }
    }

    public static void main(String[] args) {
        // char[] example = "CBA".toCharArray();
        char[] example = "MERGESORTEXAMPLE".toCharArray();

        Character[] before = new Character[example.length];
        for (int i = 0; i < example.length; i++) {
            before[i] = example[i];
        }

        new MergeSort<Character>().sort(before);
        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));
    }
}
