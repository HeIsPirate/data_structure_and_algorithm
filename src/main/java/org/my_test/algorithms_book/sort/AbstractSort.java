package org.my_test.algorithms_book.sort;

public abstract class AbstractSort<T extends Comparable<T>> {
    public abstract void sort(T[] arr);

    protected boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    protected boolean lessOrEquals(T a, T b) {
        return a.compareTo(b) <= 0;
    }

    protected boolean great(T a, T b) {
        return a.compareTo(b) > 0;
    }

    protected boolean greatOrEquals(T a, T b) {
        return a.compareTo(b) >= 0;
    }

    protected void exch(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
