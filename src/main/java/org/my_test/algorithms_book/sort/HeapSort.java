package org.my_test.algorithms_book.sort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
    @SuppressWarnings("unused")
    private void sinkRecursively(T[] arr, int index, int hi) {
        T v = arr[index];
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex > hi) {
            return;
        }

        T left = arr[leftIndex];
        T right = rightIndex <= hi ? arr[rightIndex] : null;

        if (less(v, left) || (right != null && less(v, right))) {
            int biggerIndex = right != null ? (less(left, right) ? rightIndex : leftIndex) : leftIndex;
            exch(arr, index, biggerIndex);
            sinkRecursively(arr, biggerIndex, hi);
        }
    }

    private void sink(T[] arr, int index, int hi) {
        int nowIndex = index;
        while (true) {
            T v = arr[nowIndex];
            int leftIndex = 2 * nowIndex + 1;
            int rightIndex = 2 * nowIndex + 2;

            if (leftIndex > hi) {
                break;
            }

            T left = arr[leftIndex];
            T right = rightIndex <= hi ? arr[rightIndex] : null;
            int biggerIndex = right != null ? (less(left, right) ? rightIndex : leftIndex) : leftIndex;

            if (less(v, arr[biggerIndex])) {
                exch(arr, nowIndex, biggerIndex);
                nowIndex = biggerIndex;
            } else {
                break;
            }
        }
    }

    private void buildHeap(T[] arr) {
        int min = arr.length / 2 - 1;
        for (int i = min; i >= 0; i--) {
            sink(arr, i, arr.length - 1);
        }
    }

    @Override
    public void sort(T[] arr) {
        this.buildHeap(arr);
        int index = arr.length - 1;
        while (index > 0) {
            // 依次将二叉堆最大的元素, 与index位交换
            // index是从数组尾部开始递减的, 即从数组右边尾部(从大到小)开始排序
            exch(arr, 0, index);
            sink(arr, 0, index - 1); // 数组尾部每排好序一位, 意味着二叉堆的右边界往左移动一位(缩小一位)
            index--;
        }
    }

    public static void main(String[] args) {
        char[] before1 = "HEAPSORTEXAMPLE".toCharArray();
        Character[] before = new Character[before1.length];
        for (int i = 0; i < before1.length; i++) {
            before[i] = before1[i];
        }

        System.out.println(Stream.of(before).map(String::valueOf).collect(Collectors.joining(",")));

        new HeapSort<Character>().sort(before);

        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));
    }
}
