package org.my_test.algorithms_book.searching;

public abstract class AbstractIndexedST<K extends Comparable<K>, V> extends AbstractST<K, V> {
    public abstract void deleteMin();
}
