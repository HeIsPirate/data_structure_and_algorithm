package org.my_test.algorithms_book.sort;

public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {

    @Override
    public void sort(T[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            T each_min = null;
            for (int j = i; j < length; j++) {
                if (null == each_min)
                    each_min = arr[i];
                if (each_min.compareTo(arr[j]) > 0) {
                    each_min = arr[j];
                }
            }
            arr[i] = each_min;
        }
    }
}
